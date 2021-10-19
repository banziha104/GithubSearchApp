//[app](../../../index.md)/[com.lyj.githubsearchapp.module](../index.md)/[DatabaseModule](index.md)

# DatabaseModule

[androidJvm]\
@Module

class [DatabaseModule](index.md)

## Functions

| Name | Summary |
|---|---|
| [provideGithubFavoriteUserDao](provide-github-favorite-user-dao.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [provideGithubFavoriteUserDao](provide-github-favorite-user-dao.md)(localDataBase: [LocalDataBase](../../com.lyj.githubsearchapp.data.source.local/-local-data-base/index.md)): [GithubFavoriteUserDao](../../com.lyj.githubsearchapp.data.source.local.dao/-github-favorite-user-dao/index.md) |
| [provideGithubLocalApiRepository](provide-github-local-api-repository.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [provideGithubLocalApiRepository](provide-github-local-api-repository.md)(dao: [GithubFavoriteUserDao](../../com.lyj.githubsearchapp.data.source.local.dao/-github-favorite-user-dao/index.md)): [GithubLocalApiRepository](../../com.lyj.githubsearchapp.domain.repository/-github-local-api-repository/index.md) |
| [provideLocalDatabase](provide-local-database.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [provideLocalDatabase](provide-local-database.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [LocalDataBase](../../com.lyj.githubsearchapp.data.source.local/-local-data-base/index.md) |
