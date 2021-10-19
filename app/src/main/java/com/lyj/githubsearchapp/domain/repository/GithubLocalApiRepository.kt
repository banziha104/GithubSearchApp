package com.lyj.githubsearchapp.domain.repository

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

// Local Database 와 연동되는 Repository
interface GithubLocalApiRepository : GithubUserFavoriteTableContract

/***
 * Local Database 에서 DAO가 구현해야하는 규약
 * @property findAll 저장된 데이터베이스를 읽어옮
 * @property insertOrDeleteIfExist 전달 받은 파라미터와 저장된 값을 비교해서, 이미 저장되어 있는 경우 삭제, 없는 경우 생성
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
