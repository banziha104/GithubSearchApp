package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.base.LocalDatabaseTests
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.CommitResult
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
class InsertOrDeleteUserModelUseCaseTests : LocalDatabaseTests() {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val model: GithubUserModel by lazy {
        object : GithubUserModel {
            override val userName: String
                get() = "banTestName"
            override val avatarUrl: String
                get() = "http://testAvatar"
        }
    }

    @Inject
    lateinit var insertOrDeleteUserModelUseCase : InsertOrDeleteUserModelUseCase

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun `실행테스트`(){
        // 첫 번쨰 호출시 insert 확인
        insertOrDeleteUserModelUseCase
            .execute(model)
            .testWithAwait()
            .assertNoErrors()
            .assertValue {
                it is CommitResult.Inserted
            }

        // 두 번쨰 호출시 delete 확인
        insertOrDeleteUserModelUseCase
            .execute(model)
            .testWithAwait()
            .assertNoErrors()
            .assertValue {
                it is CommitResult.Deleted
            }
    }
}
