package com.lyj.githubsearchapp.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.base.LocalDatabaseTests
import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.data.source.local.entity.GithubFavoriteUserEntity
import com.lyj.githubsearchapp.domain.repository.CommitResult
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import com.lyj.githubsearchapp.extension.testWithAwait
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.reactivex.rxjava3.core.Single
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [TestConfig.SDK_VERSION])
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@HiltAndroidTest
class GithubFavoriteUserDaoTests : LocalDatabaseTests() {
    private val entity: GithubFavoriteUserEntity by lazy {
        GithubFavoriteUserEntity("testName", "http://testAvatar")
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: GithubLocalApiRepository

    @Inject
    lateinit var dao: GithubFavoriteUserDao

    @Before
    fun `00_테스트_셋업`() {
        hiltRule.inject()
    }


    @Test
    fun `01_데이터가_비어있는지_확인`() {
        dao
            .findAllOnce()
            .testWithAwait()
            .assertValue {
                it.isEmpty()
            }
    }

    @Test
    fun `02_InsertOrDelete_호출시_Insert_되는지_확인`() {
        // 첫 번쨰 호출시 insert 확인
        Single
            .concat(
                dao.insertOrDeleteIfExists(entity),
                dao.findAllOnce()
            )
            .collect(Collectors.toList())
            .testWithAwait()
            .assertComplete()
            .assertNoErrors()
            .assertValue { (commitResult, datas) ->
                val data: GithubFavoriteUserEntity? =
                    (datas as? List<GithubFavoriteUserEntity>)?.firstOrNull()
                commitResult is CommitResult && commitResult == CommitResult.Inserted &&
                        data != null && data.login == entity.login
            }

        // 두 번쨰 호출시 delete 확인
        Single
            .concat(
                dao.insertOrDeleteIfExists(entity),
                dao.findAllOnce()
            )
            .collect(Collectors.toList())
            .testWithAwait()
            .assertComplete()
            .assertNoErrors()
            .assertValue { (commitResult, datas) ->
                val data: GithubFavoriteUserEntity? =
                    (datas as? List<GithubFavoriteUserEntity>)?.firstOrNull()
                commitResult is CommitResult && commitResult == CommitResult.Deleted &&
                        data == null
            }
    }

    @After
    fun `99_테스트_종료`() {
    }
}