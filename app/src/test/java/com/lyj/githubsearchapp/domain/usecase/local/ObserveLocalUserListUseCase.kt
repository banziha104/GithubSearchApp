package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.base.LocalDatabaseTests
import com.lyj.githubsearchapp.extension.testWithAwait
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [TestConfig.SDK_VERSION])
@HiltAndroidTest
class ObserveLocalUserListUseCaseTests : LocalDatabaseTests() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var observeLocalUserListUseCase : ObserveLocalUserListUseCase

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun `실행테스트`(){
        observeLocalUserListUseCase
            .execute()
            .testWithAwait()
            .assertNoErrors()
    }
}
