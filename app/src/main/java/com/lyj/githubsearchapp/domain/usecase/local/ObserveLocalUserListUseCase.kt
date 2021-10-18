package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveLocalUserListUseCase @Inject constructor(
    private val repository: GithubLocalApiRepository
) {
    fun execute(): Flowable<List<GithubUserModel>> =
        repository
            .observeGithubUserTable()
            .subscribeOn(Schedulers.io())
}