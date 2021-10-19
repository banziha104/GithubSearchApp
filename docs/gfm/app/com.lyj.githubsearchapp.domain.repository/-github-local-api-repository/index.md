//[app](../../../index.md)/[com.lyj.githubsearchapp.domain.repository](../index.md)/[GithubLocalApiRepository](index.md)

# GithubLocalApiRepository

[androidJvm]\
interface [GithubLocalApiRepository](index.md) : [GithubUserFavoriteTableContract](../-github-user-favorite-table-contract/index.md)

Local Database 와 연동을 추상화한 인터페이스

DAO의 기능을 명시한 [GithubUserFavoriteTableContract](../-github-user-favorite-table-contract/index.md) 를 상속

## Inherited functions

| Name | Summary |
|---|---|
| [findAll](../-github-user-favorite-table-contract/find-all.md) | [androidJvm]<br>abstract fun [findAll](../-github-user-favorite-table-contract/find-all.md)(): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
| [findByUserName](../-github-user-favorite-table-contract/find-by-user-name.md) | [androidJvm]<br>abstract fun [findByUserName](../-github-user-favorite-table-contract/find-by-user-name.md)(userName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
| [insertOrDeleteIfExist](../-github-user-favorite-table-contract/insert-or-delete-if-exist.md) | [androidJvm]<br>abstract fun [insertOrDeleteIfExist](../-github-user-favorite-table-contract/insert-or-delete-if-exist.md)(model: [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)): Single&lt;[CommitResult](../-commit-result/index.md)&gt; |

## Inheritors

| Name |
|---|
| [GithubLocalApiRepositoryImpl](../../com.lyj.githubsearchapp.data.repository/-github-local-api-repository-impl/index.md) |
