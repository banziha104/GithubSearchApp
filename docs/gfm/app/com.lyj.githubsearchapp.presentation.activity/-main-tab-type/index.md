//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.activity](../index.md)/[MainTabType](index.md)

# MainTabType

[androidJvm]\
enum [MainTabType](index.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), checkFavorite: [CheckFavorite](../-check-favorite/index.md)) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[MainTabType](index.md)&gt; 

TabLayout 의 타입

## Parameters

androidJvm

| | |
|---|---|
| title | String 리소스 |
| checkFavorite | [CheckFavorite](../-check-favorite/index.md) 현재 탭에 따라 즐겨찾기를 구현한 로직 (API 의 경우 Local 의 존재여부, Local은 즐겨찾기만 저장됨으로 모두 true) |

## Constructors

| | |
|---|---|
| [MainTabType](-main-tab-type.md) | [androidJvm]<br>private fun [MainTabType](-main-tab-type.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), checkFavorite: [CheckFavorite](../-check-favorite/index.md)) |

## Entries

| | |
|---|---|
| [API](-a-p-i/index.md) | [androidJvm]<br>[API](-a-p-i/index.md)(R.string.main_api_tab_name, CheckFavorite { targetData, localData -&gt;             targetData.userName in localData.mapNotNull { it.userName }         }) |
| [LOCAL](-l-o-c-a-l/index.md) | [androidJvm]<br>[LOCAL](-l-o-c-a-l/index.md)(R.string.main_local_tab_name, CheckFavorite { _, _ -&gt;             true         }) |

## Properties

| Name | Summary |
|---|---|
| [checkFavorite](check-favorite.md) | [androidJvm]<br>val [checkFavorite](check-favorite.md): [CheckFavorite](../-check-favorite/index.md) |
| [title](title.md) | [androidJvm]<br>val [title](title.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Inherited properties

| Name | Summary |
|---|---|
| [name](-l-o-c-a-l/index.md#-372974862%2FProperties%2F-912451524) | [androidJvm]<br>val [name](-l-o-c-a-l/index.md#-372974862%2FProperties%2F-912451524): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ordinal](-l-o-c-a-l/index.md#-739389684%2FProperties%2F-912451524) | [androidJvm]<br>val [ordinal](-l-o-c-a-l/index.md#-739389684%2FProperties%2F-912451524): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
