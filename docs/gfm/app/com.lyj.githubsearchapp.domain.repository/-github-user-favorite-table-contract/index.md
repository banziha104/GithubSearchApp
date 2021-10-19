//[app](../../../index.md)/[com.lyj.githubsearchapp.domain.repository](../index.md)/[GithubUserFavoriteTableContract](index.md)

# GithubUserFavoriteTableContract

[androidJvm]\
interface [GithubUserFavoriteTableContract](index.md)

Local Database 에서 DAO가 구현해야하는 규약

## Functions

| Name | Summary |
|---|---|
| [findAll](find-all.md) | [androidJvm]<br>abstract fun [findAll](find-all.md)(): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
| [findByUserName](find-by-user-name.md) | [androidJvm]<br>abstract fun [findByUserName](find-by-user-name.md)(userName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
| [insertOrDeleteIfExist](insert-or-delete-if-exist.md) | [androidJvm]<br>abstract fun [insertOrDeleteIfExist](insert-or-delete-if-exist.md)(model: [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)): Single&lt;[CommitResult](../-commit-result/index.md)&gt; |

## Inheritors

| Name |
|---|
| [GithubLocalApiRepository](../-github-local-api-repository/index.md) |
