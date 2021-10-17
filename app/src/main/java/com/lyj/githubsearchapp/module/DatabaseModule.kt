package com.lyj.githubsearchapp.module

import android.content.Context
import androidx.room.Room
import com.lyj.githubsearchapp.data.repository.GithubLocalApiRepositoryImpl
import com.lyj.githubsearchapp.data.source.local.LocalDataBase
import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDataBase =
        Room.databaseBuilder(
            context, LocalDataBase::class.java, "githubapp.db"
        ).build()


    @Provides
    @Singleton
    fun provideGithubFavoriteUserDao(localDataBase: LocalDataBase): GithubFavoriteUserDao =
        localDataBase.githubFavoriteUserDao()

    @Provides
    @Singleton
    fun provideGithubLocalApiRepository(
        dao : GithubFavoriteUserDao
    ): GithubLocalApiRepository = GithubLocalApiRepositoryImpl(dao)
}