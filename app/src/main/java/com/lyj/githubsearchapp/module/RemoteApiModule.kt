package com.lyj.githubsearchapp.module

import com.lyj.githubsearchapp.data.source.remote.ServiceGenerator
import com.lyj.githubsearchapp.data.source.remote.service.GithubUserApi
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
}