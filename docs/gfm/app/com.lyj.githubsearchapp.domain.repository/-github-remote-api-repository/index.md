//[app](../../../index.md)/[com.lyj.githubsearchapp.domain.repository](../index.md)/[GithubRemoteApiRepository](index.md)

# GithubRemoteApiRepository

[androidJvm]\
interface [GithubRemoteApiRepository](index.md)

원격 Github API 와 연동을 추상화한 인터페이스

## Functions

| Name | Summary |
|---|---|
| [requestGetUserList](request-get-user-list.md) | [androidJvm]<br>abstract fun [requestGetUserList](request-get-user-list.md)(searchKeyword: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |

## Inheritors

| Name |
|---|
| [GithubRemoteApiRepositoryImpl](../../com.lyj.githubsearchapp.data.repository/-github-remote-api-repository-impl/index.md) |
