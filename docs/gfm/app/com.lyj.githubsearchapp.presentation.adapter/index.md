//[app](../../index.md)/[com.lyj.githubsearchapp.presentation.adapter](index.md)

# Package com.lyj.githubsearchapp.presentation.adapter

## Types

| Name | Summary |
|---|---|
| [UserListAdapter](-user-list-adapter/index.md) | [androidJvm]<br>class [UserListAdapter](-user-list-adapter/index.md)(viewModel: [UserListAdapterViewModel](-user-list-adapter-view-model/index.md)) : [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;[UserListAdapter.UserListViewHolder](-user-list-adapter/-user-list-view-holder/index.md)&gt; <br>MainActivity 의 mainRecUser 리사이클러뷰에 적용되는 View |
| [UserListAdapterDataChanger](-user-list-adapter-data-changer/index.md) | [androidJvm]<br>interface [UserListAdapterDataChanger](-user-list-adapter-data-changer/index.md)<br>ViewModel 관련 데이터 조작을 추상화한 인터페이스 |
| [UserListAdapterViewModel](-user-list-adapter-view-model/index.md) | [androidJvm]<br>class [UserListAdapterViewModel](-user-list-adapter-view-model/index.md)(onItemClickObserver: [UserListAdapter.OnUserListAdapterItemClickedObserver](-user-list-adapter/-on-user-list-adapter-item-clicked-observer/index.md)) : [UserListAdapterDataChanger](-user-list-adapter-data-changer/index.md)<br>UserList에서 사용될 items 를 관리 UserListAdapter 에 변경된 데이터를 List 형태로 전달 |
| [UserListDataDiffUtils](-user-list-data-diff-utils/index.md) | [androidJvm]<br>class [UserListDataDiffUtils](-user-list-data-diff-utils/index.md)(oldItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;, newItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;) : [DiffUtil.Callback](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/DiffUtil.Callback.html)<br>UserList 에서 이전 데이터와 새 데이터 간의 차이를 찾기 위해 사용하는 DiffUtil |
| [UserListDataModel](-user-list-data-model/index.md) | [androidJvm]<br>interface [UserListDataModel](-user-list-data-model/index.md) : [GithubUserModel](../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)<br>UserList 에서 사용하는 모델 클래스 |
