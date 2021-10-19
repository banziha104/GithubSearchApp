package com.lyj.githubsearchapp.domain.repository

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

/**
 * Local Database 와 연동을 추상화한 인터페이스
 *
 * DAO의 기능을 명시한 [GithubUserFavoriteTableContract] 를 상속
 */
interface GithubLocalApiRepository : GithubUserFavoriteTableContract

/**
 * Local Database 에서 DAO가 구현해야하는 규약
 */
interface GithubUserFavoriteTableContract{
    fun findAll() : Single<List<GithubUserModel>>
    fun findByUserName(userName : String) : Single<List<GithubUserModel>>
    fun insertOrDeleteIfExist(model: GithubUserModel) : Single<CommitResult>
}

// insertOrDeleteIfExist 의 실행결과
sealed interface CommitResult {
    object Inserted : CommitResult
    object Deleted : CommitResult
    class Failed(t: Throwable) : CommitResult
}
