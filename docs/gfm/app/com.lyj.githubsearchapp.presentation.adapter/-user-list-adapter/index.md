//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.adapter](../index.md)/[UserListAdapter](index.md)

# UserListAdapter

[androidJvm]\
class [UserListAdapter](index.md)(viewModel: [UserListAdapterViewModel](../-user-list-adapter-view-model/index.md)) : [RecyclerView.Adapter](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter.html)&lt;[UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md)&gt; 

MainActivity 의 mainRecUser 리사이클러뷰에 적용되는 View

## Parameters

androidJvm

| | |
|---|---|
| viewModel | Adapter에 적용될 Model을 가공하는 클래스 |

## Constructors

| | |
|---|---|
| [UserListAdapter](-user-list-adapter.md) | [androidJvm]<br>fun [UserListAdapter](-user-list-adapter.md)(viewModel: [UserListAdapterViewModel](../-user-list-adapter-view-model/index.md)) |

## Types

| Name | Summary |
|---|---|
| [OnUserListAdapterItemClickedObserver](-on-user-list-adapter-item-clicked-observer/index.md) | [androidJvm]<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)<br>value class [OnUserListAdapterItemClickedObserver](-on-user-list-adapter-item-clicked-observer/index.md)(func: (Observable&lt;[Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [UserListDataModel](../-user-list-data-model/index.md)&gt;&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>UserList 에서 아이템 클릭 이벤트를 전달하는 value class |
| [UserListViewHolder](-user-list-view-holder/index.md) | [androidJvm]<br>inner class [UserListViewHolder](-user-list-view-holder/index.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html) |

## Properties

| Name | Summary |
|---|---|
| [items](items.md) | [androidJvm]<br>private var [items](items.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UserListDataModel](../-user-list-data-model/index.md)&gt; |
| [viewModel](view-model.md) | [androidJvm]<br>private val [viewModel](view-model.md): [UserListAdapterViewModel](../-user-list-adapter-view-model/index.md) |

## Inherited properties

| Name | Summary |
|---|---|
| [mHasStableIds](index.md#173773969%2FProperties%2F-912451524) | [androidJvm]<br>private val [mHasStableIds](index.md#173773969%2FProperties%2F-912451524): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [mObservable](index.md#1329912081%2FProperties%2F-912451524) | [androidJvm]<br>private val [mObservable](index.md#1329912081%2FProperties%2F-912451524): [RecyclerView.AdapterDataObservable](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.AdapterDataObservable.html) |

## Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | [androidJvm]<br>open override fun [getItemCount](get-item-count.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | [androidJvm]<br>open override fun [onBindViewHolder](on-bind-view-holder.md)(holder: [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md), position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onCreateViewHolder](on-create-view-holder.md) | [androidJvm]<br>open override fun [onCreateViewHolder](on-create-view-holder.md)(parent: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html), viewType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md) |

## Inherited functions

| Name | Summary |
|---|---|
| [bindViewHolder](index.md#-950760091%2FFunctions%2F-912451524) | [androidJvm]<br>fun [bindViewHolder](index.md#-950760091%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [createViewHolder](index.md#1423244545%2FFunctions%2F-912451524) | [androidJvm]<br>@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)<br>fun [createViewHolder](index.md#1423244545%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md) |
| [getItemId](index.md#725914875%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getItemId](index.md#725914875%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getItemViewType](index.md#714126295%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getItemViewType](index.md#714126295%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [hasObservers](index.md#1092162006%2FFunctions%2F-912451524) | [androidJvm]<br>fun [hasObservers](index.md#1092162006%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hasStableIds](index.md#16685238%2FFunctions%2F-912451524) | [androidJvm]<br>fun [hasStableIds](index.md#16685238%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [notifyDataSetChanged](index.md#-1095556076%2FFunctions%2F-912451524) | [androidJvm]<br>fun [notifyDataSetChanged](index.md#-1095556076%2FFunctions%2F-912451524)() |
| [notifyItemChanged](index.md#-1721030169%2FFunctions%2F-912451524) | [androidJvm]<br>fun [notifyItemChanged](index.md#-1721030169%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>fun [notifyItemChanged](index.md#748267402%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)p1: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) |
| [notifyItemInserted](index.md#2137269507%2FFunctions%2F-912451524) | [androidJvm]<br>fun [notifyItemInserted](index.md#2137269507%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [notifyItemMoved](index.md#-1694317867%2FFunctions%2F-912451524) | [androidJvm]<br>fun [notifyItemMoved](index.md#-1694317867%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [notifyItemRangeChanged](index.md#1769183193%2FFunctions%2F-912451524) | [androidJvm]<br>fun [notifyItemRangeChanged](index.md#1769183193%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>fun [notifyItemRangeChanged](index.md#1916975740%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)p2: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) |
| [notifyItemRangeInserted](index.md#-2104748521%2FFunctions%2F-912451524) | [androidJvm]<br>fun [notifyItemRangeInserted](index.md#-2104748521%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [notifyItemRangeRemoved](index.md#999899269%2FFunctions%2F-912451524) | [androidJvm]<br>fun [notifyItemRangeRemoved](index.md#999899269%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [notifyItemRemoved](index.md#-189254469%2FFunctions%2F-912451524) | [androidJvm]<br>fun [notifyItemRemoved](index.md#-189254469%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onAttachedToRecyclerView](index.md#-1243461790%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onAttachedToRecyclerView](index.md#-1243461790%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html)) |
| [onBindViewHolder](index.md#1559559992%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onBindViewHolder](index.md#1559559992%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;) |
| [onDetachedFromRecyclerView](index.md#-1201433889%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onDetachedFromRecyclerView](index.md#-1201433889%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.html)) |
| [onFailedToRecycleView](index.md#-535337565%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onFailedToRecycleView](index.md#-535337565%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onViewAttachedToWindow](index.md#-278916995%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onViewAttachedToWindow](index.md#-278916995%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md)) |
| [onViewDetachedFromWindow](index.md#-1380013382%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onViewDetachedFromWindow](index.md#-1380013382%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md)) |
| [onViewRecycled](index.md#994341535%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onViewRecycled](index.md#994341535%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [UserListAdapter.UserListViewHolder](-user-list-view-holder/index.md)) |
| [registerAdapterDataObserver](index.md#-149943229%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [registerAdapterDataObserver](index.md#-149943229%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [RecyclerView.AdapterDataObserver](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.AdapterDataObserver.html)) |
| [setHasStableIds](index.md#1991189249%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setHasStableIds](index.md#1991189249%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [unregisterAdapterDataObserver](index.md#607934410%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [unregisterAdapterDataObserver](index.md#607934410%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [RecyclerView.AdapterDataObserver](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.AdapterDataObserver.html)) |
