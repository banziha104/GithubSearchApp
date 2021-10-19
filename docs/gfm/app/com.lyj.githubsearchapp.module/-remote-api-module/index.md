//[app](../../../index.md)/[com.lyj.githubsearchapp.module](../index.md)/[RemoteApiModule](index.md)

# RemoteApiModule

[androidJvm]\
@Module

class [RemoteApiModule](index.md)

## Functions

| Name | Summary |
|---|---|
| [provideGithubRemoteApiRepository](provide-github-remote-api-repository.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [provideGithubRemoteApiRepository](provide-github-remote-api-repository.md)(githubUserApi: [GithubUserApi](../../com.lyj.githubsearchapp.data.source.remote.service/-github-user-api/index.md)): [GithubRemoteApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-remote-api-repository/index.md) |
| [provideGithubUserApi](provide-github-user-api.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [provideGithubUserApi](provide-github-user-api.md)(serviceGenerator: [ServiceGenerator](../../com.lyj.githubsearchapp.data.source.remote/-service-generator/index.md)): [GithubUserApi](../../com.lyj.githubsearchapp.data.source.remote.service/-github-user-api/index.md) |
