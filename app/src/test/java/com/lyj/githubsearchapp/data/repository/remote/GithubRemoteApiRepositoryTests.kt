package com.lyj.githubsearchapp.data.repository.remote

import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.domain.repository.GithubRemoteApiRepository
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
class GithubRemoteApiRepositoryTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: GithubRemoteApiRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `데이터_가져_오기_테스트`(){
        repository
            .requestGetUserList(TestConfig.SEARCH_KEYWORD, page = 1)
            .testWithAwait()
            .assertNoErrors()
            .assertComplete()
            .assertValue {
                it.isNotEmpty()
            }
    }
}