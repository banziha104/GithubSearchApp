//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.activity](../index.md)/[MainViewModel](index.md)/[requestGithubData](request-github-data.md)

# requestGithubData

[androidJvm]\
fun [requestGithubData](request-github-data.md)(tabType: [MainTabType](../-main-tab-type/index.md), searchKeyword: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): Single&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;&gt;

[tabType](request-github-data.md)과 [searchKeyword](request-github-data.md) 를 기준으로 List에서 사용할 데이터를 요청하는 메소드

#### Return

초성에 따라 그룹화된 맵을 반환(key : 초성, value : 즐겨찾기가 표시된 model [GithubModelWithFavorite](../index.md#948166379%2FClasslikes%2F-912451524)

## Parameters

androidJvm

| | |
|---|---|
| tabType | 가장 마지막에 클릭된 탭(검색 버튼 클릭) 또는 지금 클릭된 탭(TabLayout 클릭) |
| searchKeyword | 검색어 |
