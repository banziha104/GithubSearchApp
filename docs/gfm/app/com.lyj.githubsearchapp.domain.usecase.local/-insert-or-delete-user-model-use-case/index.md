//[app](../../../index.md)/[com.lyj.githubsearchapp.domain.usecase.local](../index.md)/[InsertOrDeleteUserModelUseCase](index.md)

# InsertOrDeleteUserModelUseCase

[androidJvm]\
@Singleton

class [InsertOrDeleteUserModelUseCase](index.md)@Injectconstructor(repository: [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md))

로컬에 저장 혹은 삭제하는 UseCase 만약 기존에 존재한다면 삭제 , 존재하지 않는다면 생성

## Parameters

androidJvm

| | |
|---|---|
| repository |  |

## Constructors

| | |
|---|---|
| [InsertOrDeleteUserModelUseCase](-insert-or-delete-user-model-use-case.md) | [androidJvm]<br>@Inject<br>fun [InsertOrDeleteUserModelUseCase](-insert-or-delete-user-model-use-case.md)(repository: [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [repository](repository.md) | [androidJvm]<br>private val [repository](repository.md): [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md) |

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [androidJvm]<br>fun [execute](execute.md)(model: [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)): Single&lt;[CommitResult](../../com.lyj.githubsearchapp.domain.repository/-commit-result/index.md)&gt; |
