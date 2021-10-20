package com.lyj.githubsearchapp.data.source.remote.service

import com.google.gson.annotations.SerializedName
import com.lyj.githubsearchapp.data.source.remote.entity.UserListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubUserApi{

    companion object {
        const val DEFAULT_PER_PAGE = 100
        const val DEFAULT_PAGE = 1
    }

    @GET("search/users")
    fun requestSearchUser(
        @Query("q") q : String,
        @Query("page") page : Int = DEFAULT_PAGE,
        @Query("per_page") perPage : Int = DEFAULT_PER_PAGE,
        @Query("order") order: Order = Order.ASC,
        @Query("sort") sort: Sort = Sort.BEST_MATCH,
    ) : Single<UserListResponse.Response>

    enum class Sort{
        @SerializedName("best_match")
        BEST_MATCH,
        @SerializedName("followers")
        FLOLOWERS,
        @SerializedName("repositories")
        REPOSITORIES
    }

    enum class Order{
        @SerializedName("ASC")
        ASC,
        @SerializedName("DESC")
        DESC
    }

}