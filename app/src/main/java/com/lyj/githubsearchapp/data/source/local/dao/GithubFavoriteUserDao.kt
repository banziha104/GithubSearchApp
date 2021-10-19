package com.lyj.githubsearchapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lyj.githubsearchapp.data.source.local.entity.GithubFavoriteUserEntity
import com.lyj.githubsearchapp.domain.repository.CommitResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single


@Dao
interface GithubFavoriteUserDao {

    @Query("SELECT * FROM github_favorite_user ORDER BY login ASC")
    fun findAllOnce(): Single<List<GithubFavoriteUserEntity>>

    @Query("SELECT * FROM github_favorite_user WHERE login LIKE '%' || :userName || '%'")
    fun findByUserName(userName: String) : Single<List<GithubFavoriteUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: GithubFavoriteUserEntity): Completable

    @Query("DELETE FROM github_favorite_user WHERE login == :login")
    fun delete(login: String): Completable

    fun insertOrDeleteIfExists(entity: GithubFavoriteUserEntity): Single<CommitResult> =
        findAllOnce()
            .flatMap { storedDatas ->
                if (entity.login in storedDatas.map { it.login }) {
                    delete(entity.login).andThen(Single.just(CommitResult.Deleted))
                } else {
                    insert(entity).andThen(Single.just(CommitResult.Inserted))
                }
            }

}