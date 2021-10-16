package com.lyj.githubsearchapp.data.repository

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.GithubRemoteApiRepository
import io.reactivex.rxjava3.core.Single

class GithubRemoteApiRepositoryImpl : GithubRemoteApiRepository{
    override fun requestGetUserList(searchKeyword: String): Single<List<GithubUserModel>> {
        TODO("Not yet implemented")
    }
}