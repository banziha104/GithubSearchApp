package com.lyj.githubsearchapp.data.repository.local

import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.base.LocalDatabaseTests
import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.data.source.local.entity.GithubFavoriteUserEntity
import com.lyj.githubsearchapp.domain.model.GithubUserModel
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
import java.util.stream.Collectors
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [TestConfig.SDK_VERSION])
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@HiltAndroidTest
class GithubLocalApiRepositoryTests : LocalDatabaseTests() {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: GithubLocalApiRepository

    @Inject
    lateinit var dao: GithubFavoriteUserDao

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
        hiltRule.inject()
    }


    @Test
    fun `01_데이터_조회_테스트_및_데이터가_비어있는지_확인`() {
        repository
            .findAll()
            .testWithAwait()
            .assertValue {
                println(it)
                it.isEmpty()
            }
    }

    @Test
    fun `01_InsertOrDelete_테스트`() {
        // 첫 번쨰 호출시, insert 되는지 확인
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
                commitResult is CommitResult && commitResult == CommitResult.Inserted &&
                        data != null && data.login == model.userName
            }

        // 두 번쨰 호출시, delete 되는지 확인
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
                println("data : $data ${commitResult::class.java.simpleName}")
                commitResult is CommitResult && commitResult == CommitResult.Deleted &&
                        data == null
            }
    }

    @Test
    fun `02_FindByUserName_테스트`() {
        // 첫 번쨰 호출시, insert 되는지 확인
        Single
            .concat(
                repository.insertOrDeleteIfExist(model),
                repository.findByUserName(TestConfig.SEARCH_KEYWORD)
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