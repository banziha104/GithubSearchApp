package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.CommitResult
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import com.lyj.githubsearchapp.extension.testWithAwait
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.stream.Collectors
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [TestConfig.SDK_VERSION])
class FindLocalDataByUserNameTests {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var useCase : FindLocalDataByUserNameUseCase

    @Inject
    lateinit var repository: GithubLocalApiRepository

    private val model: GithubUserModel by lazy {
        object : GithubUserModel {
            override val userName: String
                get() = "banTestName"
            override val avatarUrl: String
                get() = "http://testAvatar"
        }
    }

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun `실행테스트`(){

        Single
            .concat(
                repository.insertOrDeleteIfExist(model),
                useCase.execute(TestConfig.SEARCH_KEYWORD)
            )
            .collect(Collectors.toList())
            .testWithAwait()
            .assertComplete()
            .assertNoErrors()
            .assertValue { (commitResult, datas) ->
                commitResult is CommitResult && commitResult == CommitResult.Inserted && datas is List<*> && datas.isNotEmpty()
            }

    }
}
