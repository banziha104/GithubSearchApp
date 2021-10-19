//[app](../../../index.md)/[com.lyj.githubsearchapp.domain.usecase.remote](../index.md)/[GetRemoteUserListUseCase](index.md)

# GetRemoteUserListUseCase

[androidJvm]\
@Singleton

class [GetRemoteUserListUseCase](index.md)@Injectconstructor(githubRemoteApiRepository: [GithubRemoteApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-remote-api-repository/index.md))

원격 Github API에서 유저를 검색하는 UseCase

## Parameters

androidJvm

| | |
|---|---|
| githubRemoteApiRepository |  |

## Constructors

| | |
|---|---|
| [GetRemoteUserListUseCase](-get-remote-user-list-use-case.md) | [androidJvm]<br>@Inject<br>fun [GetRemoteUserListUseCase](-get-remote-user-list-use-case.md)(githubRemoteApiRepository: [GithubRemoteApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-remote-api-repository/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [githubRemoteApiRepository](github-remote-api-repository.md) | [androidJvm]<br>private val [githubRemoteApiRepository](github-remote-api-repository.md): [GithubRemoteApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-remote-api-repository/index.md) |

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [androidJvm]<br>fun [execute](execute.md)(userName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
