package com.lyj.githubsearchapp.data.repository

import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.data.source.local.entity.GithubFavoriteUserEntity
import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.CommitResult
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import io.reactivex.rxjava3.core.Single

class GithubLocalApiRepositoryImpl(
    private val dao: GithubFavoriteUserDao
) : GithubLocalApiRepository {
    override fun findAll(): Single<List<GithubUserModel>> =
        dao.findAllOnce().map { list -> list.map { it } }

    override fun findByUserName(userName: String): Single<List<GithubUserModel>> =
        dao.findByUserName(userName).map { list -> list.map { it } }

    override fun insertOrDeleteIfExist(model: GithubUserModel): Single<CommitResult> {
        val entity = GithubFavoriteUserEntity(
            model.userName ?: return Single.just(CommitResult.Failed(NullPointerException())),
            model.avatarUrl ?: return Single.just(CommitResult.Failed(NullPointerException())),
        )
        return dao
            .insertOrDeleteIfExists(entity)
    }
}