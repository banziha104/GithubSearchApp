package com.lyj.githubsearchapp.rules

import androidx.test.espresso.IdlingRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class OkHttpIdlingRule(
    client: OkHttpClient
) : TestRule {

    private val okHttpIdlingResource = OkHttp3IdlingResource.create("okhttp",client)
    override fun apply(base: Statement?, description: Description?): Statement =
        object : Statement() {
            override fun evaluate() {
                IdlingRegistry.getInstance().register(okHttpIdlingResource)
                base?.evaluate()
                IdlingRegistry.getInstance().unregister(okHttpIdlingResource)
            }
        }

}