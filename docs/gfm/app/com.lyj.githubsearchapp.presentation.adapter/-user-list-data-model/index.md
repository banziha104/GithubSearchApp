//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.adapter](../index.md)/[UserListDataModel](index.md)

# UserListDataModel

[androidJvm]\
interface [UserListDataModel](index.md) : [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)

UserList 에서 사용하는 모델 클래스

## Types

| Name | Summary |
|---|---|
| [Data](-data/index.md) | [androidJvm]<br>class [Data](-data/index.md)(githubUserModel: [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md), isFavorite: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), initialSound: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)) : [UserListDataModel](index.md), [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)<br>같은 초성으로 이루어진 그룹에서 첫번째가 아닌 경우의 모델 |
| [FirstData](-first-data/index.md) | [androidJvm]<br>class [FirstData](-first-data/index.md)(githubUserModel: [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md), isFavorite: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), initialSound: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)) : [UserListDataModel](index.md), [GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)<br>같은 초성으로 이루어진 그룹에서 첫번째인 경우의 모델 |

## Properties

| Name | Summary |
|---|---|
| [initialSound](initial-sound.md) | [androidJvm]<br>abstract val [initialSound](initial-sound.md): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) |
| [isFavorite](is-favorite.md) | [androidJvm]<br>abstract var [isFavorite](is-favorite.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Inherited properties

| Name | Summary |
|---|---|
| [avatarUrl](../../com.lyj.githubsearchapp.domain.model/-github-user-model/avatar-url.md) | [androidJvm]<br>abstract val [avatarUrl](../../com.lyj.githubsearchapp.domain.model/-github-user-model/avatar-url.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [userName](../../com.lyj.githubsearchapp.domain.model/-github-user-model/user-name.md) | [androidJvm]<br>abstract val [userName](../../com.lyj.githubsearchapp.domain.model/-github-user-model/user-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |

## Inheritors

| Name |
|---|
| [UserListDataModel](-first-data/index.md) |
| [UserListDataModel](-data/index.md) |
