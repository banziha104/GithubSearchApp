package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.CommitResult
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import com.lyj.githubsearchapp.domain.repository.GithubUserFavoriteTableContract
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertOrDeleteUserModelUseCase @Inject constructor(
    private val repository: GithubLocalApiRepository
) {
    fun execute(model : GithubUserModel) : Single<CommitResult> =
        repository
            .insertOrDeleteIfExist(model)
            .subscribeOn(Schedulers.io())
}