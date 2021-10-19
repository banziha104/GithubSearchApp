package com.lyj.githubsearchapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lyj.githubsearchapp.data.source.local.entity.GithubFavoriteUserEntity
import com.lyj.githubsearchapp.domain.repository.CommitResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * 즐겨찾기 테이블과 연동을 추상화한 DAO
 */
@Dao
interface GithubFavoriteUserDao {

    /**
     * 전체 데이터 조회
     */
    @Query("SELECT * FROM github_favorite_user ORDER BY login ASC")
    fun findAllOnce(): Single<List<GithubFavoriteUserEntity>>

    /**
     * userName을 포함하는 데이터만 조회
     *
     * @param userName 조회할 유저이름
     */
    @Query("SELECT * FROM github_favorite_user WHERE login LIKE '%' || :userName || '%'")
    fun findByUserName(userName: String) : Single<List<GithubFavoriteUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: GithubFavoriteUserEntity): Completable

    @Query("DELETE FROM github_favorite_user WHERE login == :login")
    fun delete(login: String): Completable


    /**
     * 동일한 즐겨찾기가 존재하는 경우 삭제, 아닌 경우 추가
     *
     * @param entity
     * @return 추가, 삭제, 실패 여부
     */
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