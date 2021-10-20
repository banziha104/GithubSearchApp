package com.lyj.githubsearchapp.presentation.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import io.reactivex.rxjava3.kotlin.merge
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RxLifecycleController {
    override val rxLifecycleObserver: RxLifecycleObserver = RxLifecycleObserver(this)

    companion object {
        private const val EVENT_THROTTLE_MILL = 500L // 각 이벤트간에 발행 간격
        private const val RETRY_UI_EVENT_ON_ERROR = 3L // UI 이벤트 에러 발생시 최대 재구독 횟수
        private const val INCREMENT_PAGE_COUNT = 1 // 현재 페이지에서 다음 페이지 간의 차이
    }

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
     * Soft 키보드에서 Action 버튼 클릭 옵저버과 검색 버튼 클릭 옵저
     *
     * @see [searchButtonActionObserver]
     */
    private val searchButtonClickObserver: Observable<Unit> by lazy {
        listOf<Observable<Unit>>(
            binding
                .mainInputEditText
                .searchButtonActionObserver()
                .disposeByOnDestory(this),
            binding
                .mainBtnSearch
                .clicks()
                .disposeByOnDestory(this)
        )
            .merge()
            .filter {
                val text = binding.mainInputEditText.text?.toString()
                if (viewModel.latestTabType == MainTabType.API && text.isNullOrBlank()) {
                    longToast(R.string.main_api_text_not_invalidated)
                    false
                } else {
                    true
                }
            }
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
            .filter { !it.view.canScrollVertically(RecyclerView.VERTICAL) && viewModel.latestTabType == MainTabType.API }
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
//            layoutManager = LinearLayoutManagerWrapper(this@MainActivity)
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
            refreshLayoutObserver.map { MainActivityEventType.Refresh }, // Swipe Refresh
            recyclerEndScollObserver.map { MainActivityEventType.EndScroll } // EndScroll
        )
            .merge()
            .throttleFirst(
                EVENT_THROTTLE_MILL,
                TimeUnit.MILLISECONDS
            ) // 여러 이벤트가 동시발행되서 예기치 못한 예외가 발생할 수 있어 200ms 스로틀링
            .flatMapSingle { event -> // 데이터 요청 부분
                val data = when (event) {
                    is MainActivityEventType.SearchButtonClicked, MainActivityEventType.Refresh -> {
                        val searchKeyword = binding.mainInputEditText.text?.toString()
                        viewModel.latestSearchKeyword = searchKeyword
                        viewModel.requestGithubData(viewModel.latestTabType, searchKeyword)
                    }
                    is MainActivityEventType.TabChanged -> viewModel.requestGithubData(
                        event.tabType,
                        viewModel.latestSearchKeyword
                    )
                    is MainActivityEventType.EndScroll -> viewModel.requestGithubData(
                        viewModel.latestTabType,
                        viewModel.latestSearchKeyword,
                        viewModel.latestPaging + INCREMENT_PAGE_COUNT
                    )

                }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        binding.mainProgressBar.visibility = View.VISIBLE
                    } // 데이터 요청 시 ProgressBar VISIBLE
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
            .retry(RETRY_UI_EVENT_ON_ERROR)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (data, event) -> // 데이터 처리 부분
                when (event) {
                    is MainActivityEventType.SearchButtonClicked, is MainActivityEventType.Refresh -> {
                        adapterController.changeData(data)
                        controlViewVisibilityAndEmptyText(viewModel.latestTabType, data)
                    }
                    is MainActivityEventType.TabChanged -> {
                        adapterController.changeData(data)
                        viewModel.latestTabType = event.tabType
                        controlViewVisibilityAndEmptyText(event.tabType, data)
                    }
                    is MainActivityEventType.EndScroll -> {
                        adapterController.addData(data)
                    }
                }
                binding.mainProgressBar.visibility = View.INVISIBLE
            }, { error ->
                when (error) {
                    is UnknownHostException -> {
                        longToast(R.string.exception_unknown_host_exception)
                    }
                    is HttpException -> {
                        longToast(
                            String.format(
                                getString(R.string.exception_http_exception),
                                error.code()
                            )
                        )
                    }
                    else -> {
                        longToast(R.string.exception_api_common)
                    }
                }
                error.printStackTrace()
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
                .throttleFirst(EVENT_THROTTLE_MILL, TimeUnit.MILLISECONDS)
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
                                if (adapterViewModel.isDataEmpty){ // 삭제 후 데이터가 비어있다면
                                    controlViewVisibilityAndEmptyText(viewModel.latestTabType, mapOf<Any?,Any?>()) // 빈 데이터 전달
                                }
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

    /**
     * [data] 가 비어있는지 여부에 따라 View 의 Visibility 를 관리
     * 데이터가 비어있다면  [currentTabType] 에 따라 EmptyTextView 의 메세지를 관리
     *
     * @param currentTabType 현재 탭 타입
     * @param data 전달 받은 데이터
     */
    private fun controlViewVisibilityAndEmptyText(currentTabType: MainTabType, data: Map<*, *>) {
        val isDataEmpty = data.isEmpty()
        binding.mainUserRecyclerView.visibility = if (!isDataEmpty) View.VISIBLE else View.GONE
        binding.mainTxtEmpty.visibility = if (isDataEmpty) View.VISIBLE else View.GONE
        if (isDataEmpty) {
            val stringResource =
                if (currentTabType == MainTabType.API) R.string.main_empty_text_api else R.string.main_empty_text_local
            binding.mainTxtEmpty.text =
                String.format(getString(R.string.main_empty_text_base), getString(stringResource))
        }
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