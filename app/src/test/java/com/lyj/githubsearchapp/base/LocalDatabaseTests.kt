package com.lyj.githubsearchapp.base

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.lyj.githubsearchapp.data.repository.GithubLocalApiRepositoryImpl
import com.lyj.githubsearchapp.data.source.local.LocalDataBase
import com.lyj.githubsearchapp.data.source.local.dao.GithubFavoriteUserDao
import com.lyj.githubsearchapp.domain.repository.GithubLocalApiRepository
import com.lyj.githubsearchapp.module.DatabaseModule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules

/***
 * LocalDataBase 를 실제로 기기에 설치된 데이터베이스가 아닌
 * InMemoryDatabase 활용을 위해 사용
 * @property UninstallModules Dependency Graph 에서 DatabaseModule 제거
 * @property BindValue 명시된 InmemoryDB를 바인딩
 */
@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
open class LocalDatabaseTests {

    @BindValue
    val database : LocalDataBase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext<Context>(), LocalDataBase::class.java
    ).build()

    @BindValue
    val dao : GithubFavoriteUserDao = database.githubFavoriteUserDao()

    @BindValue
    val repository : GithubLocalApiRepository = GithubLocalApiRepositoryImpl(dao)
}