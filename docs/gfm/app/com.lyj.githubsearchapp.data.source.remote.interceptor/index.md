//[app](../../index.md)/[com.lyj.githubsearchapp.data.source.remote.interceptor](index.md)

# Package com.lyj.githubsearchapp.data.source.remote.interceptor

## Types

| Name | Summary |
|---|---|
| [GithubHeaderInterceptor](-github-header-interceptor/index.md) | [androidJvm]<br>class [GithubHeaderInterceptor](-github-header-interceptor/index.md)(headerMap: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : Interceptor<br>Github Api 요청시 파라미터로 정의 해둔 Header를 추가하여 전송 |
| [NetworkConnectionInterceptor](-network-connection-interceptor/index.md) | [androidJvm]<br>class [NetworkConnectionInterceptor](-network-connection-interceptor/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), onCheckNetworkConnection: [NetworkConnectionInterceptor.OnCheckNetworkConnection](-network-connection-interceptor/-on-check-network-connection/index.md)) : Interceptor<br>Remote API 요청 시 마다 호출, 현재 네트워크가 가용한지 체크 |
