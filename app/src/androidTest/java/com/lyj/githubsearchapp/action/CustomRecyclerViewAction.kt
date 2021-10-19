package com.lyj.githubsearchapp.action

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

// RecyclerView와 관련된 커스텀 액션을 모아둔 객체
object CustomRecyclerViewAction {
    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }

    fun scrollToEnd() : ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "scroll RecyclerView to bottom"
            }

            override fun getConstraints(): Matcher<View> {
                return CoreMatchers.allOf<View>(
                    ViewMatchers.isAssignableFrom(RecyclerView::class.java),
                    ViewMatchers.isDisplayed()
                )
            }
            override fun perform(uiController: UiController?, view: View?) {
                val recyclerView = view as RecyclerView
                val itemCount = recyclerView.adapter?.itemCount
                val position = itemCount?.minus(1) ?: 0
                recyclerView.scrollToPosition(position)
                uiController?.loopMainThreadUntilIdle()
            }
        }
    }
}