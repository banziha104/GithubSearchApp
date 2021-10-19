package com.lyj.githubsearchapp.presentation

import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.usecase.local.FindLocalDataByUserNameUseCase
import com.lyj.githubsearchapp.domain.usecase.local.GetLocalUserListUseCase
import com.lyj.githubsearchapp.domain.usecase.local.InsertOrDeleteUserModelUseCase
import com.lyj.githubsearchapp.domain.usecase.remote.GetRemoteUserListUseCase
import com.lyj.githubsearchapp.extension.testWithAwait
import com.lyj.githubsearchapp.presentation.activity.MainTabType
import com.lyj.githubsearchapp.presentation.activity.MainViewModel
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
class MainViewModelTests {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getRemoteUserListUseCase: GetRemoteUserListUseCase

    @Inject
    lateinit var getLocalUserListUseCase: GetLocalUserListUseCase

    @Inject
    lateinit var findLocalDataByUserNameUseCase: FindLocalDataByUserNameUseCase

    @Inject
    lateinit var insertOrDeleteUserModelUseCase: InsertOrDeleteUserModelUseCase


    private val mainViewModel: MainViewModel by lazy {
        MainViewModel(
            getRemoteUserListUseCase,
            getLocalUserListUseCase,
            findLocalDataByUserNameUseCase,
            insertOrDeleteUserModelUseCase
        )
    }

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `API_테스트`() {
        mainViewModel
            .requestGithubData(
                MainTabType.API,
                TestConfig.SEARCH_KEYWORD,
            )
            .testWithAwait()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.isNotEmpty() && it.all { map ->
                    map.value.all { item ->
                        item.first.userName!!.startsWith(
                            map.key
                        )
                    }
                }
            }
    }

    @Test
    fun `Local_테스트`() {

        insertOrDeleteUserModelUseCase
            .execute(
                object : GithubUserModel {
                    override val userName: String?
                        get() = "zcxvzxcbanzcvxzvxcz"
                    override val avatarUrl: String?
                        get() = "avartarurl"

                }
            )
            .flatMap {
                mainViewModel
                    .requestGithubData(
                        MainTabType.API,
                        TestConfig.SEARCH_KEYWORD
                    )
            }
            .testWithAwait()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.isNotEmpty() && it.all { map ->
                    map.value.all { item ->
                        item.first.userName!!.startsWith(
                            map.key
                        )
                    }
                }
            }

    }
}
