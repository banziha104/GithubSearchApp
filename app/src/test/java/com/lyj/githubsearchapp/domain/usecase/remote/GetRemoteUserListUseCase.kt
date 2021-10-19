package com.lyj.githubsearchapp.domain.usecase.remote


import com.lyj.githubsearchapp.TestConfig
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

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [TestConfig.SDK_VERSION])
class GetRemoteUserListUseCaseTests {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getRemoteUserListUseCase : GetRemoteUserListUseCase

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun `실행테스트`(){
        getRemoteUserListUseCase
            .execute(TestConfig.SEARCH_KEYWORD,page = 1)
            .testWithAwait()
            .assertNoErrors()
            .assertValue {
                it.isNotEmpty()
            }
    }
}
