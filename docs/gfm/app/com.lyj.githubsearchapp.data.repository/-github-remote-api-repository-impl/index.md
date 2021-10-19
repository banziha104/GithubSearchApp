//[app](../../../index.md)/[com.lyj.githubsearchapp.data.repository](../index.md)/[GithubRemoteApiRepositoryImpl](index.md)

# GithubRemoteApiRepositoryImpl

[androidJvm]\
class [GithubRemoteApiRepositoryImpl](index.md)(githubUserApi: [GithubUserApi](../../com.lyj.githubsearchapp.data.source.remote.service/-github-user-api/index.md)) : [GithubRemoteApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-remote-api-repository/index.md)

## Properties

| Name | Summary |
|---|---|
| [githubUserApi](github-user-api.md) | [androidJvm]<br>private val [githubUserApi](github-user-api.md): [GithubUserApi](../../com.lyj.githubsearchapp.data.source.remote.service/-github-user-api/index.md) |

## Functions

| Name | Summary |
|---|---|
| [requestGetUserList](request-get-user-list.md) | [androidJvm]<br>open override fun [requestGetUserList](request-get-user-list.md)(searchKeyword: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
