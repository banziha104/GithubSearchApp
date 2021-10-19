//[app](../../../../index.md)/[com.lyj.githubsearchapp.presentation.adapter](../../index.md)/[UserListDataModel](../index.md)/[FirstData](index.md)

# FirstData

[androidJvm]\
class [FirstData](index.md)(githubUserModel: [GithubUserModel](../../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md), isFavorite: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), initialSound: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)) : [UserListDataModel](../index.md), [GithubUserModel](../../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)

같은 초성으로 이루어진 그룹에서 첫번째인 경우의 모델

## Parameters

androidJvm

| | |
|---|---|
| githubUserModel | GithubuserModel 구현을 위임 하기 위해 전달받는 원본 모델 객체 |

## Constructors

| | |
|---|---|
| [FirstData](-first-data.md) | [androidJvm]<br>fun [FirstData](-first-data.md)(githubUserModel: [GithubUserModel](../../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md), isFavorite: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), initialSound: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [initialSound](initial-sound.md) | [androidJvm]<br>open override val [initialSound](initial-sound.md): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) |
| [isFavorite](is-favorite.md) | [androidJvm]<br>open override var [isFavorite](is-favorite.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Inherited properties

| Name | Summary |
|---|---|
| [avatarUrl](../../../com.lyj.githubsearchapp.domain.model/-github-user-model/avatar-url.md) | [androidJvm]<br>open override val [avatarUrl](../../../com.lyj.githubsearchapp.domain.model/-github-user-model/avatar-url.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [userName](../../../com.lyj.githubsearchapp.domain.model/-github-user-model/user-name.md) | [androidJvm]<br>open override val [userName](../../../com.lyj.githubsearchapp.domain.model/-github-user-model/user-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
