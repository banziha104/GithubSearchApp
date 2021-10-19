//[app](../../../index.md)/[com.lyj.githubsearchapp.presentation.adapter](../index.md)/[UserListDataDiffUtils](index.md)

# UserListDataDiffUtils

[androidJvm]\
class [UserListDataDiffUtils](index.md)(oldItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;, newItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;) : [DiffUtil.Callback](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/DiffUtil.Callback.html)

UserList 에서 이전 데이터와 새 데이터 간의 차이를 찾기 위해 사용하는 DiffUtil

## See also

androidJvm

| | |
|---|---|
| [androidx.recyclerview.widget.DiffUtil.Callback](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/DiffUtil.Callback.html) |  |

## Parameters

androidJvm

| | |
|---|---|
| oldItems | 기존 아이템 |
| newItems | 새 아이템 |

## Constructors

| | |
|---|---|
| [UserListDataDiffUtils](-user-list-data-diff-utils.md) | [androidJvm]<br>fun [UserListDataDiffUtils](-user-list-data-diff-utils.md)(oldItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;, newItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [newItems](new-items.md) | [androidJvm]<br>private val [newItems](new-items.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt; |
| [oldItems](old-items.md) | [androidJvm]<br>private val [oldItems](old-items.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[GithubUserModel](../../com.lyj.githubsearchapp.domain.model/-github-user-model/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | [androidJvm]<br>open override fun [areContentsTheSame](are-contents-the-same.md)(oldItemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), newItemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [areItemsTheSame](are-items-the-same.md) | [androidJvm]<br>open override fun [areItemsTheSame](are-items-the-same.md)(oldItemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), newItemPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getNewListSize](get-new-list-size.md) | [androidJvm]<br>open override fun [getNewListSize](get-new-list-size.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getOldListSize](get-old-list-size.md) | [androidJvm]<br>open override fun [getOldListSize](get-old-list-size.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Inherited functions

| Name | Summary |
|---|---|
| [getChangePayload](index.md#-1911713805%2FFunctions%2F-912451524) | [androidJvm]<br>@[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)<br>open fun [getChangePayload](index.md#-1911713805%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
