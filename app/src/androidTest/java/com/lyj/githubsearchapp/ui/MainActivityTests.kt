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
@LargeTest
@HiltAndroidTest
@UninstallModules(DatabaseModule::class) // Inmemory Database 사용을 위해 제거
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

    /**
     * 액티비티 테스트 시나리오
     *
     * 1. EditText에 검색어 입력 테스트
     * 2. 검색버튼 클릭 및 프로그래스바 테스트 (프로그래스바 테스트는 API 완료 시점이 불분명해 우선 제외)
     * 3. 네트워크 완료 후 데이터 갯수 테스트
     * 4. Local 탭으로 이동 후 비어있는지 확인
     * 5. API 탭으로 북귀, 첫 번째 아이템 즐겨찾기 클릭
     * 6. 첫 번째 아이템 클릭 후 이미지 적용 확인
     * 7. Local 탭으로 가서 첫 번째 아이템 반영 및 데이터 갯수 확인
     * 8. Local 탭에서 즐겨찾기 해제 후 지워지는지확인
     * 9. API 탭으로 돌아와 첫 번째 즐겨찾기가 지워졌는지 확인
     * 10. Swipe 후 Refresh 되는지 확인
     * 11. 최하단으로 이동해서 추가로 데이터를 받아오는지 확인 (Infinite Scroll)
     */
    @Test
    fun `메인액티비티_테스트`() {
        // 1. EditText에 검색어 입력 테스트
        onView(withId(R.id.mainInputEditText))
            .perform(replaceText(searchText))
            .check(matches(withText(searchText)))

        // 2. 검색버튼 클릭 및 프로그래스바 테스트 (프로그래스바 테스트는 API 완료 시점이 불분명해 우선 제외)
        onView(withId(R.id.mainBtnSearch))
            .perform(click())

//        onView(withId(R.id.mainProgressBar))
//            .check(matches(isDisplayed()))

        await(2000)

        //  3. 네트워크 완료 후 데이터 갯수 테스트
        recyclerViewInteraction
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))


        // 4. Local 탭으로 이동 후 비어있는지 확인
        clickTab(MainTabType.LOCAL)

        recyclerViewInteraction
            .check(matches(CustomRecyclerViewMatcher.withDataEmpty()))

        // 5. API 탭으로 북귀, 첫 번째 아이템 즐겨찾기 클릭
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

        // 6. 첫 번째 아이템 클릭 후 이미지 적용 확인
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

        // 7. Local 탭으로 가서 첫 번째 아이템 반영 및 데이터 갯수 확인

        clickTab(MainTabType.LOCAL)

        recyclerViewInteraction
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))

        //  8. Local 탭에서 즐겨찾기 해제 후 지워지는지확인

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

        // 9. API 탭으로 돌아와 첫 번째 즐겨찾기가 지워졌는지 확인
        clickTab(MainTabType.API)

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


        // 10. Swipe 후 Refresh 되는지 확인

        recyclerViewInteraction
            .perform(swipeDown())


//        onView(withId(R.id.mainProgressBar))
//            .check(matches(isDisplayed()))


        //  11. 최하단으로 이동해서 추가로 데이터를 받아오는지 확인 (Infinite Scroll)

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