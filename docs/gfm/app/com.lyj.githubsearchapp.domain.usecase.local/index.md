//[app](../../index.md)/[com.lyj.githubsearchapp.domain.usecase.local](index.md)

# Package com.lyj.githubsearchapp.domain.usecase.local

## Types

| Name | Summary |
|---|---|
| [FindLocalDataByUserNameUseCase](-find-local-data-by-user-name-use-case/index.md) | [androidJvm]<br>@Singleton<br>class [FindLocalDataByUserNameUseCase](-find-local-data-by-user-name-use-case/index.md)@Injectconstructor(repository: [GithubLocalApiRepository](../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md))<br>전달받은 userName을 포함하는 즐겨찾기만 가져오는 UseCase |
| [GetLocalUserListUseCase](-get-local-user-list-use-case/index.md) | [androidJvm]<br>@Singleton<br>class [GetLocalUserListUseCase](-get-local-user-list-use-case/index.md)@Injectconstructor(repository: [GithubLocalApiRepository](../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md))<br>전체 즐겨찾기 데이터를 가져오는 UseCase |
| [InsertOrDeleteUserModelUseCase](-insert-or-delete-user-model-use-case/index.md) | [androidJvm]<br>@Singleton<br>class [InsertOrDeleteUserModelUseCase](-insert-or-delete-user-model-use-case/index.md)@Injectconstructor(repository: [GithubLocalApiRepository](../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md))<br>로컬에 저장 혹은 삭제하는 UseCase 만약 기존에 존재한다면 삭제 , 존재하지 않는다면 생성 |
