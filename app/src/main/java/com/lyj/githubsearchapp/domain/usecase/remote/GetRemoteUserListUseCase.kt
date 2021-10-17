package com.lyj.githubsearchapp.domain.usecase.remote

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.GithubRemoteApiRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRemoteUserListUseCase @Inject constructor(
    private val githubRemoteApiRepository: GithubRemoteApiRepository
){
    fun execute(userName: String) : Single<List<GithubUserModel>> =
        githubRemoteApiRepository
            .requestGetUserList(userName)
            .subscribeOn(Schedulers.io())
}
