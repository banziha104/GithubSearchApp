package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FindLocalDataByUserNameUseCase @Inject constructor(
    private val repository: GithubLocalApiRepository
) {
    fun execute(userName : String) = repository.findByUserName(userName).subscribeOn(Schedulers.io())
}