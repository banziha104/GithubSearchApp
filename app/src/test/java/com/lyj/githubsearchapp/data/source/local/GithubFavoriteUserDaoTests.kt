package com.lyj.githubsearchapp.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.base.LocalDatabaseTests
import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.data.source.local.entity.GithubFavoriteUserEntity
import com.lyj.githubsearchapp.domain.repository.GithubUserFavoriteTableContract.CommitResult
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
@Config(sdk = [TestConfig.SDK_VERSION])
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GithubFavoriteUserDaoTests : LocalDatabaseTests(){
    private lateinit var dao: GithubFavoriteUserDao
    private val entity: GithubFavoriteUserEntity by lazy {
        GithubFavoriteUserEntity("testName", "http://testAvatar")
    }


    @Before
    fun `00_테스트_셋업`() {
        dao = database.githubFavoriteUserDao()
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
                commitResult is CommitResult && commitResult == CommitResult.INSERTED &&
                        data != null && data.login == entity.login
            }
    }

    @Test
    fun `03_한번_더_InsertOrDelete_호출시_데이터가_삭제되는지_확인`() {
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
                commitResult is CommitResult && commitResult == CommitResult.DELETED &&
                        data == null
            }
    }

    @After
    fun `99_테스트_종료`() {
        database.close()
    }
}