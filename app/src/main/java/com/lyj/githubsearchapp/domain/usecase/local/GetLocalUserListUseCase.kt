package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.domain.model.GithubUserModel
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 전체 즐겨찾기 데이터를 가져오는 UseCase
 */
@Singleton
class GetLocalUserListUseCase @Inject constructor(
    private val repository: GithubLocalApiRepository
) {
    fun execute(): Single<List<GithubUserModel>> =
        repository
            .findAll()
            .subscribeOn(Schedulers.io())
}