package com.lyj.githubsearchapp.domain.usecase.local

import com.lyj.githubsearchapp.domain.repository.GithubUserFavoriteTableContract
import io.reactivex.rxjava3.core.Single

class InsertOrDeleteUserModelUseCase {
    fun execute() : Single<GithubUserFavoriteTableContract.CommitResult> = TODO()
}