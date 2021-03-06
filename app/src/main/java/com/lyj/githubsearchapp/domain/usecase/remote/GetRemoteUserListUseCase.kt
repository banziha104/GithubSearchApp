package com.lyj.githubsearchapp.domain.usecase.remote

import com.lyj.githubsearchapp.data.source.remote.service.GithubUserApi
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.GithubRemoteApiRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 원격 Github API에서 유저를 검색하는 UseCase
 *
 * @param githubRemoteApiRepository
 */
@Singleton
class GetRemoteUserListUseCase @Inject constructor(
    private val githubRemoteApiRepository: GithubRemoteApiRepository
){
    fun execute(userName: String, page : Int? = null) : Single<List<GithubUserModel>> =
        githubRemoteApiRepository
            .requestGetUserList(userName,page ?: GithubUserApi.DEFAULT_PAGE)
            .subscribeOn(Schedulers.io())
}
