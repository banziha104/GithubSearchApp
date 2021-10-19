//[app](../../../index.md)/[com.lyj.githubsearchapp.data.source.remote.interceptor](../index.md)/[GithubHeaderInterceptor](index.md)

# GithubHeaderInterceptor

[androidJvm]\
class [GithubHeaderInterceptor](index.md)(headerMap: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) : Interceptor

Github Api 요청시 파라미터로 정의 해둔 Header를 추가하여 전송

## Parameters

androidJvm

| | |
|---|---|
| headerMap | 요청시 변경할 Header 명세 |

## Constructors

| | |
|---|---|
| [GithubHeaderInterceptor](-github-header-interceptor.md) | [androidJvm]<br>fun [GithubHeaderInterceptor](-github-header-interceptor.md)(headerMap: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [headerMap](header-map.md) | [androidJvm]<br>private val [headerMap](header-map.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | [androidJvm]<br>open override fun [intercept](intercept.md)(chain: Interceptor.Chain): Response |
