package com.lyj.githubsearchapp.domain.repository

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

// Local Database 와 연동되는 Repository
interface GithubLocalApiRepository : GithubUserFavoriteTableContract

/***
 * Local Database 에서 DAO가 구현해야하는
 * @property observeGithubUserTable 테이블 변경 사항 관찰
 * @property insertOrDeleteIfExist 전달 받은 파라미터와 저장된 값을 비교해서, 이미 저장되어 있는 경우 삭제, 없는 경우 생성
 */
interface GithubUserFavoriteTableContract{
    fun observeGithubUserTable() : Flowable<List<GithubUserModel>>
    fun insertOrDeleteIfExist(model: GithubUserModel) : Single<CommitResult>

    // insertOrDeleteIfExist 의 실행결과
    enum class CommitResult{
        INSERTED, DELETED
    }
}
