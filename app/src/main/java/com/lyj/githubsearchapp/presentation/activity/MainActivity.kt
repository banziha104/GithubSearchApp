package com.lyj.githubsearchapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.view.clicks
import com.lyj.githubsearchapp.common.extension.android.TabLayoutEventType
import com.lyj.githubsearchapp.common.extension.android.selectedObserver
import com.lyj.githubsearchapp.common.extension.android.unwrappedValue
import com.lyj.githubsearchapp.common.extension.lang.disposeByOnDestory
import com.lyj.githubsearchapp.common.rx.RxLifecycleController
import com.lyj.githubsearchapp.common.rx.RxLifecycleObserver
import com.lyj.githubsearchapp.databinding.ActivityMainBinding
import com.lyj.githubsearchapp.domain.repository.CommitResult
import com.lyj.githubsearchapp.presentation.adapter.UserListAdapter
import com.lyj.githubsearchapp.presentation.adapter.UserListAdapterController
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.Observables.combineLatest
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RxLifecycleController {
    override val rxLifecycleObserver: RxLifecycleObserver = RxLifecycleObserver(this)

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter(UserListAdapter.OnUserListAdapterItemClickedObserver { itemClickObserver ->
            itemClickObserver
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .flatMapSingle { (position, model) ->
                    Single.zip(
                        viewModel.insertOrDeleteUserModelUseCase.execute(model),
                        Single.just(position)
                    ) { a, b -> a to b }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (result, position) ->
                    when (result) {
                        is CommitResult.Deleted -> {
                            if (viewModel.latestTabType.unwrappedValue == MainTabType.LOCAL) {
                                adapterController.notifyRemoveItem(position)
                            }else{
                                adapterController.notifyChangeItem(position)
                            }
                        }
                        is CommitResult.Inserted -> adapterController.notifyChangeItem(position)
                        is CommitResult.Failed -> TODO()
                    }
                }, {
                    it.printStackTrace()
                })
        })
    }

    private val adapterController: UserListAdapterController = userListAdapter

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
        observeLocalData()
    }


    private fun observeLocalData() {
        Observable
            .combineLatest(
                binding
                    .mainTabLayout
                    .selectedObserver(MainViewModel.DEFAULT_TAB.ordinal)
                    .disposeByOnDestory(this)
                    .filter { it == TabLayoutEventType.SELECTED && it.position != null }
                    .map { MainTabType.values()[it.position!!] },
                binding.mainBtnSearch.clicks()
            ) { currentTabType, _ ->
                currentTabType
            }
            .filter {
                val text = binding.mainInputEditText.text
                val validation = text != null && text.isNotBlank()
                if (!validation) Toast.makeText(this, "검색할 수 없는 문자", Toast.LENGTH_SHORT).show()
                validation
            }
            .flatMapSingle { currentTabType ->
                Single.zip(
                    when (currentTabType) {
                        MainTabType.API -> {
                            Single.zip(
                                viewModel.getRemoteUserListUseCase
                                    .execute(binding.mainInputEditText.text!!.toString()),
                                viewModel.getLocalUserListUseCase.execute()
                            ) { remoteData, localData ->
                                viewModel.getData(remoteData, localData, currentTabType)
                            }
                        }
                        MainTabType.LOCAL -> {
                            viewModel.getLocalUserListUseCase.execute().map {
                                viewModel.getData(it, it, currentTabType)
                            }
                        }
                    },
                    Single.just(currentTabType)
                ){ data, currentTabType ->
                    data to currentTabType
                }
            }
            .disposeByOnDestory(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (data, currentTabType) ->
                if (currentTabType == viewModel.latestTabType.value) {
                    adapterController.addData(data)
                } else {
                    adapterController.changeData(data)
                }
                viewModel.latestTabType.value = currentTabType
            }, {
                it.printStackTrace()
            })
    }

}