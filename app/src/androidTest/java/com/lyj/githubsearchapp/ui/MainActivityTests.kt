package com.lyj.githubsearchapp.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.android.material.tabs.TabLayout
import com.lyj.githubsearchapp.R
import com.lyj.githubsearchapp.presentation.activity.MainActivity
import com.lyj.githubsearchapp.presentation.adapter.UserListAdapter
import com.lyj.githubsearchapp.rules.OkHttpIdlingRule
import okhttp3.OkHttpClient
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.lyj.githubsearchapp.action.CustomRecyclerViewAction
import com.lyj.githubsearchapp.action.CustomTableLayoutAction
import com.lyj.githubsearchapp.action.CustomViewAction
import com.lyj.githubsearchapp.data.repository.GithubLocalApiRepositoryImpl
import com.lyj.githubsearchapp.data.source.local.LocalDataBase
import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import com.lyj.githubsearchapp.matcher.CustomRecyclerViewMatcher
import com.lyj.githubsearchapp.module.DatabaseModule
import com.lyj.githubsearchapp.presentation.activity.MainTabType
import dagger.hilt.android.testing.*
import org.hamcrest.Description
import org.hamcrest.core.IsNot.not
import org.junit.*
import javax.inject.Singleton


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class MainActivityTests {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @BindValue
    @Singleton
    val localDataBase: LocalDataBase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext<Context>(), LocalDataBase::class.java
    ).build()

    @BindValue
    @Singleton
    val githubFavoriteUserDao: GithubFavoriteUserDao = localDataBase.githubFavoriteUserDao()

    @BindValue
    @Singleton
    val githubLocalApiRepository: GithubLocalApiRepository =
        GithubLocalApiRepositoryImpl(githubFavoriteUserDao)

    @Inject
    lateinit var client: OkHttpClient

    private val okHttpIdlingResource by lazy {
        OkHttp3IdlingResource.create("okhttp", client)
    }
    private val searchText = "ban"
    private val recyclerViewInteraction: ViewInteraction by lazy {
        onView(withId(R.id.mainUserRecyclerView))
    }


    @Before
    fun setUp() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(okHttpIdlingResource)
    }

    private fun clickTab(tabType: MainTabType) {
        onView(withId(R.id.mainTabLayout))
            .perform(CustomTableLayoutAction.selectTabAtPosition(tabType.ordinal))
        await(1000)
    }

    private fun await(long: Long) {
        onView(isRoot()).perform(CustomViewAction.waitFor(long))
    }

    @Test
    fun `01_검색_입력_테스트`() {
        // EditText 테스트
        onView(withId(R.id.mainInputEditText))
            .perform(replaceText(searchText))
            .check(matches(withText(searchText)))

        // 검색 버튼 클릭 및 프로그레스바 테스트
        onView(withId(R.id.mainBtnSearch))
            .perform(click())

//        onView(withId(R.id.mainProgressBar))
//            .check(matches(isDisplayed()))

        await(2000)

        // 데이터 로드 후 아이템 갯수 테스트
        recyclerViewInteraction
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))


        // 로컬 데이터베이스 이동 및 아이템 갯수 테스트
        clickTab(MainTabType.LOCAL)

        recyclerViewInteraction
            .check(matches(CustomRecyclerViewMatcher.withDataEmpty()))

        // API 탭으로 돌아와 첫번쨰 아이템 클릭
        clickTab(MainTabType.API)

        recyclerViewInteraction
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserListAdapter.UserListViewHolder>(
                    0,
                    CustomRecyclerViewAction.clickChildViewWithId(R.id.userItemBtnFavorite)
                )
            )
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))

        await(1000)

        // 첫번째 아이템 클릭후 이미지 적용 확인
        recyclerViewInteraction
            .check(
                matches(
                    CustomRecyclerViewMatcher
                        .atPositionWithBitmapDrawable(
                            0,
                            R.id.userItemBtnFavorite,
                            R.drawable.ic_star_normal
                        )
                )
            )

        clickTab(MainTabType.LOCAL)

        // Local 탭에서 아이템 갯수 확인 및 첫번째 아이템 클릭
        recyclerViewInteraction
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))

        recyclerViewInteraction
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserListAdapter.UserListViewHolder>(
                    0,
                    CustomRecyclerViewAction.clickChildViewWithId(R.id.userItemBtnFavorite)
                )
            )


        await(1000)

        recyclerViewInteraction
            .check(matches(CustomRecyclerViewMatcher.withDataEmpty()))

        clickTab(MainTabType.API)

        // API 탭에서 첫번쨰 아이템 이미지 확인 및 Data 확인
        recyclerViewInteraction
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))
            .check(
                matches(
                    CustomRecyclerViewMatcher
                        .atPositionWithBitmapDrawable(
                            0,
                            R.id.userItemBtnFavorite,
                            R.drawable.ic_star_inverted
                        )
                )
            )


        // Refresh 테스트
        recyclerViewInteraction
            .perform(swipeDown())


//        // 무한 스크롤 테스트
//        // 데이터 불러오기 테스트
//
        var firstLoadItemCount = 0

        recyclerViewInteraction
            .check { view, noViewFoundException ->

                assert(noViewFoundException == null)

                if (view is RecyclerView) {
                    firstLoadItemCount = view.adapter?.itemCount ?: 0

                    assert(firstLoadItemCount != 0)
                } else {
                    assert(false)
                }
            }
//        // 리스트를 최하단으로 이동
        recyclerViewInteraction
            .perform(CustomRecyclerViewAction.scrollToEnd())


        await(2000)

        recyclerViewInteraction
            .check { view, noViewFoundException ->

                assert(noViewFoundException == null)

                if (view is RecyclerView) {
                    val itemCount = view.adapter?.itemCount ?: 0
                    assert(firstLoadItemCount != 0 && itemCount > firstLoadItemCount)
                } else {
                    assert(false)
                }
            }
    }


    @After
    fun `99_테스트_끝`() {
        IdlingRegistry.getInstance().unregister(okHttpIdlingResource)
    }
}