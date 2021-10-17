package com.lyj.githubsearchapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.data.source.local.entity.GithubFavoriteUserEntity

@Database(
    entities = [GithubFavoriteUserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase(){
    abstract fun githubFavoriteUserDao() : GithubFavoriteUserDao
}