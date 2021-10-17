package com.lyj.githubsearchapp.module

import com.lyj.githubsearchapp.data.repository.GithubRemoteApiRepositoryImpl
import com.lyj.githubsearchapp.data.source.remote.ServiceGenerator
import com.lyj.githubsearchapp.data.source.remote.service.GithubUserApi
import com.lyj.githubsearchapp.domain.repository.GithubRemoteApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteApiModule {
    @Provides
    @Singleton
    fun provideGithubUserApi(
        serviceGenerator: ServiceGenerator
    ): GithubUserApi = serviceGenerator.generateService(GithubUserApi::class.java)

    @Provides
    @Singleton
    fun provideGithubRemoteApiRepository(
        githubUserApi: GithubUserApi
    ): GithubRemoteApiRepository = GithubRemoteApiRepositoryImpl(githubUserApi)
}