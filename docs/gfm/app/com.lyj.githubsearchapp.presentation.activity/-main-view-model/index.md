//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.activity](../index.md)/[MainViewModel](index.md)

# MainViewModel

[androidJvm]\
class [MainViewModel](index.md)@Injectconstructor(getRemoteUserListUseCase: [GetRemoteUserListUseCase](../../com.lyj.githubsearchapp.domain.usecase.remote/-get-remote-user-list-use-case/index.md), getLocalUserListUseCase: [GetLocalUserListUseCase](../../com.lyj.githubsearchapp.domain.usecase.local/-get-local-user-list-use-case/index.md), findLocalDataByUserNameUseCase: [FindLocalDataByUserNameUseCase](../../com.lyj.githubsearchapp.domain.usecase.local/-find-local-data-by-user-name-use-case/index.md), insertOrDeleteUserModelUseCase: [InsertOrDeleteUserModelUseCase](../../com.lyj.githubsearchapp.domain.usecase.local/-insert-or-delete-user-model-use-case/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [findLocalDataByUserNameUseCase](find-local-data-by-user-name-use-case.md) | [androidJvm]<br>private val [findLocalDataByUserNameUseCase](find-local-data-by-user-name-use-case.md): [FindLocalDataByUserNameUseCase](../../com.lyj.githubsearchapp.domain.usecase.local/-find-local-data-by-user-name-use-case/index.md) |
| [getLocalUserListUseCase](get-local-user-list-use-case.md) | [androidJvm]<br>private val [getLocalUserListUseCase](get-local-user-list-use-case.md): [GetLocalUserListUseCase](../../com.lyj.githubsearchapp.domain.usecase.local/-get-local-user-list-use-case/index.md) |
| [getRemoteUserListUseCase](get-remote-user-list-use-case.md) | [androidJvm]<br>private val [getRemoteUserListUseCase](get-remote-user-list-use-case.md): [GetRemoteUserListUseCase](../../com.lyj.githubsearchapp.domain.usecase.remote/-get-remote-user-list-use-case/index.md) |
| [insertOrDeleteUserModelUseCase](insert-or-delete-user-model-use-case.md) | [androidJvm]<br>val [insertOrDeleteUserModelUseCase](insert-or-delete-user-model-use-case.md): [InsertOrDeleteUserModelUseCase](../../com.lyj.githubsearchapp.domain.usecase.local/-insert-or-delete-user-model-use-case/index.md) |
| [latestTabType](latest-tab-type.md) | [androidJvm]<br>var [latestTabType](latest-tab-type.md): [MainTabType](../-main-tab-type/index.md)<br>가장 마지막에 클릭된 탭 |

## Inherited properties

| Name | Summary |
|---|---|
| [mBagOfTags](index.md#2127905085%2FProperties%2F-912451524) | [androidJvm]<br>@[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)<br>private val [mBagOfTags](index.md#2127905085%2FProperties%2F-912451524): [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;? |
| [mCleared](index.md#581728969%2FProperties%2F-912451524) | [androidJvm]<br>private val [mCleared](index.md#581728969%2FProperties%2F-912451524): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [matchFavorite](match-favorite.md) | [androidJvm]<br>private fun [matchFavorite](match-favorite.md)(targetData: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;, localData: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;, tabType: [MainTabType](../-main-tab-type/index.md)): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;<br>즐겨 찾기 여부를 표시하는 메소드 |
| [requestGithubData](request-github-data.md) | [androidJvm]<br>fun [requestGithubData](request-github-data.md)(tabType: [MainTabType](../-main-tab-type/index.md), searchKeyword: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): Single&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;&gt;<br>[tabType](request-github-data.md)과 [searchKeyword](request-github-data.md) 를 기준으로 List에서 사용할 데이터를 요청하는 메소드 |
| [splitInitializeSoundData](split-initialize-sound-data.md) | [androidJvm]<br>private fun [splitInitializeSoundData](split-initialize-sound-data.md)(list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;&gt;<br>첫 글자를 통해 초성을 얻고, 해당 초성으로 그룹화하는 메소드 |

## Inherited functions

| Name | Summary |
|---|---|
| [clear](index.md#-1936886459%2FFunctions%2F-912451524) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>fun [clear](index.md#-1936886459%2FFunctions%2F-912451524)() |
| [getTag](index.md#-215894976%2FFunctions%2F-912451524) | [androidJvm]<br>open fun &lt;[T](index.md#-215894976%2FFunctions%2F-912451524) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [getTag](index.md#-215894976%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [T](index.md#-215894976%2FFunctions%2F-912451524) |
| [onCleared](index.md#-1930136507%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onCleared](index.md#-1930136507%2FFunctions%2F-912451524)() |
| [setTagIfAbsent](index.md#-1567230750%2FFunctions%2F-912451524) | [androidJvm]<br>open fun &lt;[T](index.md#-1567230750%2FFunctions%2F-912451524) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [setTagIfAbsent](index.md#-1567230750%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), newValue: [T](index.md#-1567230750%2FFunctions%2F-912451524)): [T](index.md#-1567230750%2FFunctions%2F-912451524) |
