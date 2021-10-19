package com.lyj.githubsearchapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.view.clicks
import com.lyj.githubsearchapp.common.extension.android.TabLayoutEventType
import com.lyj.githubsearchapp.common.extension.android.searchButtonActionObserver
import com.lyj.githubsearchapp.common.extension.android.selectedObserver
import com.lyj.githubsearchapp.common.extension.lang.disposeByOnDestory
import com.lyj.githubsearchapp.common.rx.RxLifecycleController
import com.lyj.githubsearchapp.common.rx.RxLifecycleObserver
import com.lyj.githubsearchapp.databinding.ActivityMainBinding
import com.lyj.githubsearchapp.domain.model.GithubUserModel
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
        UserListAdapterViewModel(getListItemClickObserver())
    }

    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter(adapterViewModel)
    }

    /**
     * TabLayout 에서 아이템이 클릭을 관찰하는 옵저버
     * SELECTED 상태, 이전에 동일하지 않은 탭을 클릭하는 경우만 발행
     *
     * @see selectedObserver
     */
    private val tabLayoutItemClickedObserver: Observable<MainTabType> by lazy {
        binding
            .mainTabLayout
            .selectedObserver()
            .disposeByOnDestory(this)
            .filter { it == TabLayoutEventType.SELECTED && it.position != null }
            .map { MainTabType.values()[it.position!!] } // position 이 nullable 인 것은 위의 filter에서 확인하여 강제 언래핑
            .filter { it != viewModel.latestTabType }
    }

    /**
     * 검색 버튼 클릭 옵저버
     */
    private val searchButtonClickObserver: Observable<Unit> by lazy {
        binding
            .mainBtnSearch
            .clicks()
            .disposeByOnDestory(this)
    }

    /**
     * Soft 키보드에서 Action 버튼 클릭 옵저버
     *
     * @see [searchButtonActionObserver]
     */
    private val softKeyboardInputListener : Observable<Unit> by lazy {
        binding
            .mainInputEditText
            .searchButtonActionObserver()
            .disposeByOnDestory(this)
    }

    /**
     * UserListAdapter 조작하는 추상 클래스
     *
     * @see [UserListAdapterDataChanger]
     */
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

    /**
     * 데이터에 영향을 주는 UI 클래스를 관찰하는 옵저버들을 구독하는 메소드
     * merge를 통해 명시된 옵저버 중 하나라도 발행하면 데이터 변경
     *
     * @see [MainActivityEventType] sealed class로 이벤트 타입 정의
     * @see [MainViewModel.requestGithubData] 데이터 발행 메소드
     */
    private fun observeUiEventWithAffectListData() {
        Observable
            .merge<MainActivityEventType>(
                tabLayoutItemClickedObserver.map { MainActivityEventType.TabChanged(it) }, // tabLayout click
                searchButtonClickObserver.map { MainActivityEventType.SearchButtonClicked }, // mainSearchButton click
                softKeyboardInputListener.map { MainActivityEventType.SearchButtonClicked } // action button click
            )
            .flatMapSingle { event ->
                Single.zip(
                    when (event) {
                        is MainActivityEventType.SearchButtonClicked -> {
                            viewModel.requestGithubData(
                                viewModel.latestTabType,
                                binding.mainInputEditText.text?.toString()
                            )
                        }
                        is MainActivityEventType.TabChanged -> {
                            val text = binding.mainInputEditText.text
                            if (event.tabType == MainTabType.API) {
                                if (text != null) {
                                    viewModel.requestGithubData(
                                        event.tabType,
                                        text.toString()
                                    )
                                } else {
                                    Single.just(mapOf())
                                }
                            } else {
                                viewModel.requestGithubData(event.tabType,text?.toString())
                            }
                        }
                    },
                    Single.just(event)
                ) { data, event ->
                    data to event
                }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { binding.mainProgressBar.visibility = View.VISIBLE } // 데이터 요청 시 ProgressBar VISIBLE
                    .doOnSuccess { binding.mainProgressBar.visibility = View.GONE }  // 데이터 요청 완료 시 ProgressBar GONE
            }
            .disposeByOnDestory(this)
            .observeOn(AndroidSchedulers.mainThread())
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

    /**
     * UserListAdapter 에서 아이템 클릭을 관찰하는 옵저버 구독
     *
     * @return [UserListAdapter.OnUserListAdapterItemClickedObserver] 어뎁터에서 사용할 반환객체
     */
    private fun getListItemClickObserver(): UserListAdapter.OnUserListAdapterItemClickedObserver =
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
                .disposeByOnDestory(this)
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
}

/**
 * Main Event 에서 발행되는 이벤트 모음
 */
sealed interface MainActivityEventType {
    object SearchButtonClicked : MainActivityEventType
    class TabChanged(val tabType: MainTabType) : MainActivityEventType
}