package com.lyj.githubsearchapp.ui

import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
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
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import okhttp3.OkHttpClient
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.platform.app.InstrumentationRegistry
import com.lyj.githubsearchapp.action.CustomRecyclerViewAction
import com.lyj.githubsearchapp.action.CustomTableLayoutAction
import com.lyj.githubsearchapp.matcher.CustomRecyclerViewMatcher
import org.hamcrest.Description
import org.hamcrest.core.IsNot.not


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
@HiltAndroidTest
class MainActivityTests {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var okHttpClient = OkHttpIdlingRule(OkHttpClient())

    @get:Rule(order = 2)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    private lateinit var client : OkHttpClient

    private val searchText = "ban"
    private val recyclerViewInteraction: ViewInteraction by lazy {
        onView(withId(R.id.mainUserRecyclerView))
    }

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `01_검색_입력_테스트`() {
        onView(withId(R.id.mainInputEditText))
            .perform(replaceText(searchText))
            .check(matches(withText(searchText)))
    }

    @Test
    fun `02_검색_버튼_클릭_및_프로그래스바_테스트`() {
        onView(withId(R.id.mainBtnSearch))
            .perform(click())

        onView(withId(R.id.mainProgressBar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `03_초기_유저_리스트_아이템_갯수_테스트`() {
        recyclerViewInteraction
            .check(matches(CustomRecyclerViewMatcher.withDataEmpty()))
    }

    @Test
    fun `04_로컬_데이터베이스로_이동_및_아이템_갯수_체크`() {
        onView(withId(R.id.mainTabLayout))
            .perform(CustomTableLayoutAction.selectTabAtPosition(1))
        recyclerViewInteraction
            .check(matches(CustomRecyclerViewMatcher.withDataEmpty()))
    }

    @Test
    fun `05_API_탭으로_돌아와_아이템_첫번째_아이템_클릭`() {
        recyclerViewInteraction
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserListAdapter.UserListViewHolder>(
                    0,
                    CustomRecyclerViewAction.clickChildViewWithId(R.id.userItemBtnFavorite)
                )
            )
            .check(
                matches(
                    CustomRecyclerViewMatcher.atPositionWithDrawable<ImageButton>(
                        0,
                        R.id.userItemBtnFavorite,
                        R.drawable.ic_star_normal
                    )
                )
            )
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))
    }


    @Test
    fun `06_Local_탭_에서_아이템_갯수_확인_및_첫번째_아이템_클릭`() {
        recyclerViewInteraction
            .check(matches(not(CustomRecyclerViewMatcher.withDataEmpty())))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserListAdapter.UserListViewHolder>(
                    0,
                    CustomRecyclerViewAction.clickChildViewWithId(R.id.userItemBtnFavorite)
                )
            )
            .check(
                matches(
                    CustomRecyclerViewMatcher.atPositionWithDrawable<ImageButton>(
                        0,
                        R.id.userItemBtnFavorite,
                        R.drawable.ic_star_inverted
                    )
                )
            )
            .check(matches(CustomRecyclerViewMatcher.withDataEmpty()))
    }

    @Test
    fun `07_API_탭_에서_아이템_첫번째_아이템_확인`() {
        recyclerViewInteraction
            .check(matches(CustomRecyclerViewMatcher.withDataEmpty()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserListAdapter.UserListViewHolder>(
                    0,
                    CustomRecyclerViewAction.clickChildViewWithId(R.id.userItemBtnFavorite)
                )
            )
            .check(matches(CustomRecyclerViewMatcher.atPositionWithDrawable<ImageButton>(0,R.id.userItemBtnFavorite,R.drawable.ic_star_normal)))
    }
}