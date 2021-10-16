package com.lyj.githubsearchapp.domain.repository

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface GithubRemoteApiRepository {
    fun requestGetUserList(searchKeyword: String) : Single<List<GithubUserModel>>
}