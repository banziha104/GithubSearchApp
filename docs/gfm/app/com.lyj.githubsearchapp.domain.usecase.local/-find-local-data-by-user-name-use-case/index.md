//[app](../../../index.md)/[com.lyj.githubsearchapp.domain.usecase.local](../index.md)/[FindLocalDataByUserNameUseCase](index.md)

# FindLocalDataByUserNameUseCase

[androidJvm]\
@Singleton

class [FindLocalDataByUserNameUseCase](index.md)@Injectconstructor(repository: [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md))

전달받은 userName을 포함하는 즐겨찾기만 가져오는 UseCase

## Constructors

| | |
|---|---|
| [FindLocalDataByUserNameUseCase](-find-local-data-by-user-name-use-case.md) | [androidJvm]<br>@Inject<br>fun [FindLocalDataByUserNameUseCase](-find-local-data-by-user-name-use-case.md)(repository: [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [repository](repository.md) | [androidJvm]<br>private val [repository](repository.md): [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md) |

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [androidJvm]<br>fun [execute](execute.md)(userName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): @NonNullSingle&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt; |
