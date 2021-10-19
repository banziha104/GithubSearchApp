package com.lyj.githubsearchapp.data.repository

import com.lyj.githubsearchapp.data.source.remote.service.GithubUserApi
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.GithubRemoteApiRepository
import io.reactivex.rxjava3.core.Single

class GithubRemoteApiRepositoryImpl(
    private val githubUserApi: GithubUserApi
) : GithubRemoteApiRepository {
    override fun requestGetUserList(searchKeyword: String): Single<List<GithubUserModel>> =
        githubUserApi.requestSearchUser(searchKeyword).map {
            it.items ?: listOf()
        }
}