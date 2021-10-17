package com.lyj.githubsearchapp.data.repository.local

import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.base.LocalDatabaseTests
import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.data.source.local.entity.GithubFavoriteUserEntity
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.CommitResult
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import com.lyj.githubsearchapp.extension.testWithAwait
import io.reactivex.rxjava3.core.Single
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.stream.Collectors
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [TestConfig.SDK_VERSION])
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GithubLocalApiRepositoryTests : LocalDatabaseTests() {
    private val model: GithubUserModel by lazy {
        object : GithubUserModel{
            override val userName: String
                get() = "banTestName"
            override val avatarUrl: String
                get() = "http://testAvatar"
        }
    }

    @Before
    fun `00_테스트_셋업`() {

    }


    @Test
    fun `01_데이터가_비어있는지_확인`() {
        repository
            .observeGithubUserTable()
            .testWithAwait()
            .assertValue {
                println(it)
                it.isEmpty()
            }
    }

    @Test
    fun `02_InsertOrDelete_호출시_Insert_되는지_확인`() {
        Single
            .concat(
                repository.insertOrDeleteIfExist(model),
                dao.findAllOnce()
            )
            .collect(Collectors.toList())
            .testWithAwait()
            .assertComplete()
            .assertNoErrors()
            .assertValue { (commitResult, datas) ->
                val data: GithubFavoriteUserEntity? =
                    (datas as? List<GithubFavoriteUserEntity>)?.firstOrNull()

                val data2 = datas as? List<GithubFavoriteUserEntity>
                println("data : $data $data2 ${commitResult::class.java.simpleName}")
                commitResult is CommitResult && commitResult == CommitResult.Inserted &&
                        data != null && data.login == model.userName
            }
    }

    @Test
    fun `03_한번_더_InsertOrDelete_호출시_데이터가_삭제되는지_확인`() {
        Single
            .concat(
                repository.insertOrDeleteIfExist(model),
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

}