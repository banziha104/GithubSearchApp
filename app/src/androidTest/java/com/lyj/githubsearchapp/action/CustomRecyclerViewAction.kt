package com.lyj.githubsearchapp.action

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
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
}