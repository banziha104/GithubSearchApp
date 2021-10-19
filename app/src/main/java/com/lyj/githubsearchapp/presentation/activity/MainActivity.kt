package com.lyj.githubsearchapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.recyclerview.RecyclerViewScrollEvent
import com.jakewharton.rxbinding4.recyclerview.scrollEvents
import com.jakewharton.rxbinding4.view.clicks
import com.lyj.githubsearchapp.R
import com.lyj.githubsearchapp.common.extension.android.*
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
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.merge
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
    private val softKeyboardInputObserver: Observable<Unit> by lazy {
        binding
            .mainInputEditText
            .searchButtonActionObserver()
            .disposeByOnDestory(this)
    }

    /**
     * Swipe Refresh 동작 옵저버
     * API 일떄만 Paging을 진행함으로 API로 필터링
     *
     * @see [refreshObserver]
     */
    private val refreshLayoutObserver: Observable<Unit> by lazy {
        binding
            .mainSwipeRefreshLayout
            .refreshObserver()
            .throttleFirst(1, TimeUnit.MILLISECONDS)
    }

    /**
     * ScrollView가 하단에 도달했는지 알아보는 옵저버
     * API 일떄만 Paging을 진행함으로 API로 필터링
     *
     * @see [refreshObserver]
     */
    private val recyclerEndScollObserver: Observable<RecyclerViewScrollEvent> by lazy {
        binding
            .mainUserRecyclerView
            .scrollEvents()
            .disposeByOnDestory(this)
            .filter { !it.view.canScrollVertically(1) && viewModel.latestTabType == MainTabType.API }
            .throttleFirst(1, TimeUnit.SECONDS)
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
        listOf<Observable<MainActivityEventType>>(
            tabLayoutItemClickedObserver.map { MainActivityEventType.TabChanged(it) }, // tabLayout click
            searchButtonClickObserver.map { MainActivityEventType.SearchButtonClicked }, // mainSearchButton click
            softKeyboardInputObserver.map { MainActivityEventType.SearchButtonClicked }, // action button click
            refreshLayoutObserver.map { MainActivityEventType.Refresh }, // Swipe Refresh
            recyclerEndScollObserver.map { MainActivityEventType.EndScroll } // EndScroll
        )
            .merge()
            .throttleFirst(
                200,
                TimeUnit.MILLISECONDS
            ) // 여러 이벤트가 동시발행되서 예기치 못한 예외가 발생할 수 있어 200ms 스로틀링
            .flatMapSingle { event ->
                val data = when (event) {
                    is MainActivityEventType.SearchButtonClicked, MainActivityEventType.Refresh -> {
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
                            viewModel.requestGithubData(event.tabType, text?.toString())
                        }
                    }
                    is MainActivityEventType.EndScroll -> {
                        viewModel.requestGithubData(
                            viewModel.latestTabType,
                            viewModel.latestSearchKeyword,
                            viewModel.latestPaging + 1
                        )
                    }
                }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { binding.mainProgressBar.visibility = View.VISIBLE } // 데이터 요청 시 ProgressBar VISIBLE
                    .doOnSuccess {  // 데이터 요청 완료 시 ProgressBar GONE
                        binding.mainProgressBar.visibility = View.GONE
                        binding.mainSwipeRefreshLayout.isRefreshing = false
                    }
                    .doOnError { // 데이터 에러 시 ProgressBar GONE
                        binding.mainProgressBar.visibility = View.GONE
                        binding.mainSwipeRefreshLayout.isRefreshing = false
                    }

                Single.zip(data, Single.just(event)) { data, event -> data to event }
            }
            .disposeByOnDestory(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (data, event) ->
                when (event) {
                    is MainActivityEventType.SearchButtonClicked, is MainActivityEventType.Refresh -> {
                        adapterController.changeData(data)
                    }
                    is MainActivityEventType.TabChanged -> {
                        adapterController.changeData(data)
                        viewModel.latestTabType = event.tabType
                    }
                    is MainActivityEventType.EndScroll -> {
                        adapterController.addData(data)
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
                        is CommitResult.Failed -> longToast(R.string.main_fail_insert_or_delete)
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
    object Refresh : MainActivityEventType
    object EndScroll : MainActivityEventType
    class TabChanged(val tabType: MainTabType) : MainActivityEventType
}