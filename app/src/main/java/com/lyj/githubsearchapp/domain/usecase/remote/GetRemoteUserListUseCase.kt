package com.lyj.githubsearchapp.domain.usecase.remote

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

class GetRemoteUserListUseCase {
    fun execute(userName: String) : Single<List<GithubUserModel>> = TODO()
}