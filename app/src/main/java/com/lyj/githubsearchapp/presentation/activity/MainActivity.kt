package com.lyj.githubsearchapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.view.clicks
import com.lyj.githubsearchapp.common.extension.android.TabLayoutEventType
import com.lyj.githubsearchapp.common.extension.android.selectedObserver
import com.lyj.githubsearchapp.common.extension.lang.disposeByOnDestory
import com.lyj.githubsearchapp.common.rx.RxLifecycleController
import com.lyj.githubsearchapp.common.rx.RxLifecycleObserver
import com.lyj.githubsearchapp.databinding.ActivityMainBinding
import com.lyj.githubsearchapp.domain.repository.CommitResult
import com.lyj.githubsearchapp.presentation.adapter.UserListAdapter
import com.lyj.githubsearchapp.presentation.adapter.UserListAdapterDataChanger
import com.lyj.githubsearchapp.presentation.adapter.UserListAdapterViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RxLifecycleController {
    override val rxLifecycleObserver: RxLifecycleObserver = RxLifecycleObserver(this)

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapterViewModel: UserListAdapterViewModel by lazy {
        UserListAdapterViewModel(
            UserListAdapter.OnUserListAdapterItemClickedObserver { itemClickObserver ->
                itemClickObserver
                    .throttleFirst(500, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.io())
                    .flatMapSingle { (position, model) ->
                        Single.zip(
                            viewModel.insertOrDeleteUserModelUseCase.execute(model),
                            Single.just(position),
                            Single.just(model)
                        ) { a, b, c -> Triple(a, b, c) }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ (result, position, model) ->
                        when (result) {
                            is CommitResult.Deleted -> {
                                if (viewModel.latestTabType == MainTabType.LOCAL) {
                                    adapterController.notifyRemoveItem(model, position)
                                } else {
                                    adapterController.notifyChangeItem(model, position)
                                }
                            }
                            is CommitResult.Inserted -> adapterController.notifyChangeItem(
                                model,
                                position
                            )
                            is CommitResult.Failed -> TODO()
                        }
                    }, {
                        it.printStackTrace()
                    })
            }
        )
    }

    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter(adapterViewModel)
    }

    private val adapterController: UserListAdapterDataChanger = adapterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeView()
        observeRxSource()
    }

    private fun initializeView() {
        binding.mainUserRecyclerView.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun observeRxSource() {
        observeUiEventWithAffectListData()
    }


    private fun observeUiEventWithAffectListData() {
        Observable
            .merge<MainActivityEventType>(
                binding
                    .mainTabLayout
                    .selectedObserver()
                    .disposeByOnDestory(this)
                    .filter { it == TabLayoutEventType.SELECTED && it.position != null }
                    .map { MainTabType.values()[it.position!!] }
                    .filter { it != viewModel.latestTabType }
                    .map { MainActivityEventType.TabChanged(it) },
                binding
                    .mainBtnSearch
                    .clicks()
                    .map { MainActivityEventType.SearchButtonClicked }
                    .filter {
                        val text = binding.mainInputEditText.text
                        val validation = text != null && text.isNotBlank()
                        if (!validation) Toast.makeText(this, "검색할 수 없는 문자", Toast.LENGTH_SHORT).show()
                        validation
                    }
            )
            .flatMapSingle { event ->
                Single.zip(
                    when (event) {
                        is MainActivityEventType.SearchButtonClicked -> {
                            viewModel.requestGithubData(
                                viewModel.latestTabType,
                                binding.mainInputEditText.text!!.toString()
                            )
                        }
                        is MainActivityEventType.TabChanged -> {
                            viewModel.requestGithubData(
                                event.tabType,
                                binding.mainInputEditText.text!!.toString()
                            )
                        }
                    },
                    Single.just(event)
                ) { data, event ->
                    data to event
                }
            }
            .disposeByOnDestory(this)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { binding.mainProgressBar.visibility = View.VISIBLE }
            .subscribe({ (data, event) ->
                when (event) {
                    is MainActivityEventType.SearchButtonClicked -> {
                        adapterController.changeData(data)
                    }
                    is MainActivityEventType.TabChanged -> {
                        adapterController.changeData(data)
                        viewModel.latestTabType = event.tabType
                    }
                }
                binding.mainProgressBar.visibility = View.INVISIBLE
            }, {
                it.printStackTrace()
            })
    }
}

sealed interface MainActivityEventType {
    object SearchButtonClicked : MainActivityEventType
    class TabChanged(val tabType: MainTabType) : MainActivityEventType
}