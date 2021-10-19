//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.adapter](../index.md)/[UserListAdapterDataChanger](index.md)

# UserListAdapterDataChanger

[androidJvm]\
interface [UserListAdapterDataChanger](index.md)

ViewModel 관련 데이터 조작을 추상화한 인터페이스

## Functions

| Name | Summary |
|---|---|
| [addData](add-data.md) | [androidJvm]<br>abstract fun [addData](add-data.md)(data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../../com.lyj.githubsearchapp.presentation.activity/index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../../com.lyj.githubsearchapp.presentation.activity/index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;) |
| [changeData](change-data.md) | [androidJvm]<br>abstract fun [changeData](change-data.md)(data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../../com.lyj.githubsearchapp.presentation.activity/index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../../com.lyj.githubsearchapp.presentation.activity/index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;) |
| [notifyChangeItem](notify-change-item.md) | [androidJvm]<br>abstract fun [notifyChangeItem](notify-change-item.md)(model: [UserListDataModel](../-user-list-data-model/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [notifyRemoveItem](notify-remove-item.md) | [androidJvm]<br>abstract fun [notifyRemoveItem](notify-remove-item.md)(model: [UserListDataModel](../-user-list-data-model/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Inheritors

| Name |
|---|
| [UserListAdapterViewModel](../-user-list-adapter-view-model/index.md) |
