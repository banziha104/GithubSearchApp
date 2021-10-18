package com.lyj.githubsearchapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.lyj.githubsearchapp.presentation.adapter.UserListAdapterController
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
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

    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter(UserListAdapter.OnUserListAdapterItemClickedObserver { itemClickObserver ->
            itemClickObserver
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .flatMapSingle { (position, model) ->
                    Log.d("myTest", "여기실행?")
                    Single.zip(
                        viewModel.insertOrDeleteUserModelUseCase.execute(model),
                        Single.just(position)
                    ) { a, b -> a to b }
                }
                .delay(50, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (result, position) ->
                    Log.d("myTest", "result $result position $position")
                    when (result) {
                        is CommitResult.Deleted -> adapterController.notifyRemoveItem(position)
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
        Flowable
            .combineLatest(
                viewModel.observeLocalUserListUseCase.execute(),
                binding
                    .mainTabLayout
                    .selectedObserver(MainViewModel.DEFAULT_TAB.ordinal)
                    .disposeByOnDestory(this)
                    .filter { it == TabLayoutEventType.SELECTED && it.position != null }
                    .map { MainTabType.values()[it.position!!] }
                    .toFlowable(BackpressureStrategy.LATEST),
                binding.mainBtnSearch.clicks().toFlowable(BackpressureStrategy.LATEST)
            ) { localData, currentTab, _ ->
                localData to currentTab
            }
            .filter {
                val text = binding.mainInputEditText.text
                val validation = text != null && text.isNotBlank()
                if (!validation) Toast.makeText(this, "검색할 수 없는 문자", Toast.LENGTH_SHORT).show()
                validation
            }
            .concatMapEager { (localData, currentTab) ->
                Flowable.combineLatest(
                    when (currentTab) {
                        MainTabType.API -> {
                            viewModel.getRemoteUserListUseCase
                                .execute(binding.mainInputEditText.text!!.toString())
                                .toFlowable()
                                .map {
                                    viewModel.getData(it, localData)
                                }
                        }
                        MainTabType.LOCAL -> {
                            Flowable
                                .just(viewModel.getData(localData, localData))
                        }
                    },
                    Flowable.just(currentTab)
                ) { data, currentTab ->
                    data to currentTab
                }
            }
            .disposeByOnDestory(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (userDataList, currentTab) ->
                if (currentTab == viewModel.currentTabType.value) {
                    adapterController.addData(userDataList)
                } else {
                    adapterController.changeData(userDataList)
                }
                viewModel.currentTabType.value = currentTab
            }, {
                it.printStackTrace()
            })
    }

}