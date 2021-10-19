//[app](../../../index.md)/[com.lyj.githubsearchapp.domain.usecase.local](../index.md)/[GetLocalUserListUseCase](index.md)

# GetLocalUserListUseCase

[androidJvm]\
@Singleton

class [GetLocalUserListUseCase](index.md)@Injectconstructor(repository: [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md))

전체 즐겨찾기 데이터를 가져오는 UseCase

## Constructors

| | |
|---|---|
| [GetLocalUserListUseCase](-get-local-user-list-use-case.md) | [androidJvm]<br>@Inject<br>fun [GetLocalUserListUseCase](-get-local-user-list-use-case.md)(repository: [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [repository](repository.md) | [androidJvm]<br>private val [repository](repository.md): [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md) |

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [androidJvm]<br>fun [execute](execute.md)(): Single&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
