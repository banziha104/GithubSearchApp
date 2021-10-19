package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.CommitResult
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import com.lyj.githubsearchapp.domain.repository.GithubUserFavoriteTableContract
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 로컬에 저장 혹은 삭제하는 UseCase
 * 만약 기존에 존재한다면 삭제 , 존재하지 않는다면 생성
 *
 * @param repository
 */
@Singleton
class InsertOrDeleteUserModelUseCase @Inject constructor(
    private val repository: GithubLocalApiRepository
) {
    fun execute(model : GithubUserModel) : Single<CommitResult> =
        repository
            .insertOrDeleteIfExist(model)
            .subscribeOn(Schedulers.io())
}