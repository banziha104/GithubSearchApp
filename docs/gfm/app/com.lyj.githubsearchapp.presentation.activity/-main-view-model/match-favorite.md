//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.activity](../index.md)/[MainViewModel](index.md)/[matchFavorite](match-favorite.md)

# matchFavorite

[androidJvm]\
private fun [matchFavorite](match-favorite.md)(targetData: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;, localData: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;, tabType: [MainTabType](../-main-tab-type/index.md)): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;

즐겨 찾기 여부를 표시하는 메소드

#### Return

초성에 따라 그룹화된 맵을 반환(key : 초성, value : 즐겨찾기가 표시된 model [GithubModelWithFavorite](../index.md#948166379%2FClasslikes%2F-912451524)

## Parameters

androidJvm

| | |
|---|---|
| targetData | 비교 대상 리스트 |
| localData | 즐겨찾기 리스트 |
