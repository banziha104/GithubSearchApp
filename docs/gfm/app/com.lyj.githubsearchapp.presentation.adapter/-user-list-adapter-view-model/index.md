//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.adapter](../index.md)/[UserListAdapterViewModel](index.md)

# UserListAdapterViewModel

[androidJvm]\
class [UserListAdapterViewModel](index.md)(onItemClickObserver: [UserListAdapter.OnUserListAdapterItemClickedObserver](../-user-list-adapter/-on-user-list-adapter-item-clicked-observer/index.md)) : [UserListAdapterDataChanger](../-user-list-adapter-data-changer/index.md)

UserList에서 사용될 items 를 관리 UserListAdapter 에 변경된 데이터를 List 형태로 전달

## Constructors

| | |
|---|---|
| [UserListAdapterViewModel](-user-list-adapter-view-model.md) | [androidJvm]<br>fun [UserListAdapterViewModel](-user-list-adapter-view-model.md)(onItemClickObserver: [UserListAdapter.OnUserListAdapterItemClickedObserver](../-user-list-adapter/-on-user-list-adapter-item-clicked-observer/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [dataEventDriver](data-event-driver.md) | [androidJvm]<br>val [dataEventDriver](data-event-driver.md): BehaviorSubject&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UserListDataModel](../-user-list-data-model/index.md)&gt;&gt;<br>데이터 변경을 발행하는 Subject |
| [items](items.md) | [androidJvm]<br>private val [items](items.md): [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[InitialSound](../../com.lyj.githubsearchapp.presentation.activity/index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../../com.lyj.githubsearchapp.presentation.activity/index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;<br>ViewModel 에서 관리하는 데이터 셋 초성을 key로, 즐겨찾기가 표시된 모델 리스트를 value로 가진 Map 형태이며, View 에 전달 시, List 형태로 가공 |
| [onItemClickObserver](on-item-click-observer.md) | [androidJvm]<br>val [onItemClickObserver](on-item-click-observer.md): [UserListAdapter.OnUserListAdapterItemClickedObserver](../-user-list-adapter/-on-user-list-adapter-item-clicked-observer/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addData](add-data.md) | [androidJvm]<br>open override fun [addData](add-data.md)(data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../../com.lyj.githubsearchapp.presentation.activity/index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../../com.lyj.githubsearchapp.presentation.activity/index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;) |
| [changeData](change-data.md) | [androidJvm]<br>open override fun [changeData](change-data.md)(data: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[InitialSound](../../com.lyj.githubsearchapp.presentation.activity/index.md#-1583565500%2FClasslikes%2F-912451524), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubModelWithFavorite](../../com.lyj.githubsearchapp.presentation.activity/index.md#948166379%2FClasslikes%2F-912451524)&gt;&gt;) |
| [notifyChangeItem](notify-change-item.md) | [androidJvm]<br>open override fun [notifyChangeItem](notify-change-item.md)(model: [UserListDataModel](../-user-list-data-model/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [notifyRemoveItem](notify-remove-item.md) | [androidJvm]<br>open override fun [notifyRemoveItem](notify-remove-item.md)(model: [UserListDataModel](../-user-list-data-model/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [submitEvent](submit-event.md) | [androidJvm]<br>private fun [submitEvent](submit-event.md)()<br>이벤트를 발행하는 메소드 |
