//[app](../../../index.md)/[com.lyj.githubsearchapp.data.source.remote.service](../index.md)/[GithubUserApi](index.md)/[requestSearchUser](request-search-user.md)

# requestSearchUser

[androidJvm]\

@GET(value = "search/users")

abstract fun [requestSearchUser](request-search-user.md)(@Query(value = "q")q: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = "page")page: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1, @Query(value = "per_page")perPage: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = DEFAULT_PER_PAGE, @Query(value = "order")order: [GithubUserApi.Order](-order/index.md) = Order.ASC, @Query(value = "sort")sort: [GithubUserApi.Sort](-sort/index.md) = Sort.BEST_MATCH): Single&lt;[UserListResponse.Response](../../com.lyj.githubsearchapp.data.source.remote.entity/-user-list-response/-response/index.md)&gt;
