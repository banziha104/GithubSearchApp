//[app](../../../index.md)/[com.lyj.githubsearchapp.data.repository](../index.md)/[GithubLocalApiRepositoryImpl](index.md)

# GithubLocalApiRepositoryImpl

[androidJvm]\
class [GithubLocalApiRepositoryImpl](index.md)(dao: [GithubFavoriteUserDao](../../com.lyj.githubsearchapp.data.source.local.dao/-github-favorite-user-dao/index.md)) : [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md)

## Properties

| Name | Summary |
|---|---|
| [dao](dao.md) | [androidJvm]<br>private val [dao](dao.md): [GithubFavoriteUserDao](../../com.lyj.githubsearchapp.data.source.local.dao/-github-favorite-user-dao/index.md) |

## Functions

| Name | Summary |
|---|---|
| [findAll](find-all.md) | [androidJvm]<br>open override fun [findAll](find-all.md)(): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
| [findByUserName](find-by-user-name.md) | [androidJvm]<br>open override fun [findByUserName](find-by-user-name.md)(userName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
| [insertOrDeleteIfExist](insert-or-delete-if-exist.md) | [androidJvm]<br>open override fun [insertOrDeleteIfExist](insert-or-delete-if-exist.md)(model: [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)): Single&lt;[CommitResult](../../com.lyj.githubsearchapp.domain.repository/-commit-result/index.md)&gt; |
