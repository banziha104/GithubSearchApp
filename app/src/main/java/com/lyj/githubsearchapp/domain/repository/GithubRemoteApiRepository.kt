package com.lyj.githubsearchapp.domain.repository

import com.lyj.githubsearchapp.data.source.remote.service.GithubUserApi
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

/**
 * 원격 Github API 와 연동을 추상화한 인터페이스
 */
interface GithubRemoteApiRepository {
    fun requestGetUserList(searchKeyword: String, page : Int = GithubUserApi.DEFAULT_PAGE) : Single<List<GithubUserModel>>
}