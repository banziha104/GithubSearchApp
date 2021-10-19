//[app](../../../index.md)/[com.lyj.githubsearchapp.data.source.local.dao](../index.md)/[GithubFavoriteUserDao](index.md)

# GithubFavoriteUserDao

[androidJvm]\
interface [GithubFavoriteUserDao](index.md)

즐겨찾기 테이블과 연동을 추상화한 DAO

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract fun [delete](delete.md)(login: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Completable |
| [findAllOnce](find-all-once.md) | [androidJvm]<br>abstract fun [findAllOnce](find-all-once.md)(): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubFavoriteUserEntity](../../com.lyj.githubsearchapp.data.source.local.entity/-github-favorite-user-entity/index.md)&gt;&gt;<br>전체 데이터 조회 |
| [findByUserName](find-by-user-name.md) | [androidJvm]<br>abstract fun [findByUserName](find-by-user-name.md)(userName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubFavoriteUserEntity](../../com.lyj.githubsearchapp.data.source.local.entity/-github-favorite-user-entity/index.md)&gt;&gt;<br>userName을 포함하는 데이터만 조회 |
| [insert](insert.md) | [androidJvm]<br>abstract fun [insert](insert.md)(entity: [GithubFavoriteUserEntity](../../com.lyj.githubsearchapp.data.source.local.entity/-github-favorite-user-entity/index.md)): Completable |
| [insertOrDeleteIfExists](insert-or-delete-if-exists.md) | [androidJvm]<br>open fun [insertOrDeleteIfExists](insert-or-delete-if-exists.md)(entity: [GithubFavoriteUserEntity](../../com.lyj.githubsearchapp.data.source.local.entity/-github-favorite-user-entity/index.md)): Single&lt;[CommitResult](../../com.lyj.githubsearchapp.domain.repository/-commit-result/index.md)&gt;<br>동일한 즐겨찾기가 존재하는 경우 삭제, 아닌 경우 추가 |
