package com.lyj.githubsearchapp.data.repository

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import com.lyj.githubsearchapp.domain.repository.GithubUserFavoriteTableContract
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class GithubLocalApiRepositoryImpl : GithubLocalApiRepository{
    override fun observeGithubUserTable(): Flowable<List<GithubUserModel>> {
        TODO("Not yet implemented")
    }

    override fun insertOrDeleteIfExist(model: GithubUserModel): Single<GithubUserFavoriteTableContract.CommitResult> {
        TODO("Not yet implemented")
    }
}