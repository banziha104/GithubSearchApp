package com.lyj.githubsearchapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lyj.githubsearchapp.domain.model.GithubUserModel

@Entity(tableName = "github_favorite_user")
data class GithubFavoriteUserEntity(


    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "avatar_url")
    override val avatarUrl: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
) : GithubUserModel {
    override val userName: String
        get() = login
}