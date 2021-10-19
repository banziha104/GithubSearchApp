//[app](../../../index.md)/[com.lyj.githubsearchapp.data.source.remote.interceptor](../index.md)/[NetworkConnectionInterceptor](index.md)

# NetworkConnectionInterceptor

[androidJvm]\
class [NetworkConnectionInterceptor](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), onCheckNetworkConnection: [NetworkConnectionInterceptor.OnCheckNetworkConnection](-on-check-network-connection/index.md)) : Interceptor

<ul><li></li></ul>

Remote API 요청 시 마다 호출, 현재 네트워크가 가용한지 체크

## Parameters

androidJvm

| | |
|---|---|
| context |  |
| onCheckNetworkConnection | network가 가용한지 체크 될떄마다 전달되는 inline class 기반 고차 함수 |

## Constructors

| | |
|---|---|
| [NetworkConnectionInterceptor](-network-connection-interceptor.md) | [androidJvm]<br>fun [NetworkConnectionInterceptor](-network-connection-interceptor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), onCheckNetworkConnection: [NetworkConnectionInterceptor.OnCheckNetworkConnection](-on-check-network-connection/index.md)) |

## Types

| Name | Summary |
|---|---|
| [OnCheckNetworkConnection](-on-check-network-connection/index.md) | [androidJvm]<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)<br>value class [OnCheckNetworkConnection](-on-check-network-connection/index.md)(callBack: (isConnected: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | [androidJvm]<br>private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [isConnected](is-connected.md) | [androidJvm]<br>private val [isConnected](is-connected.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>호출시 현재 네트워크가 가용한지를 반환하는 프로퍼티 |
| [onCheckNetworkConnection](on-check-network-connection.md) | [androidJvm]<br>private val [onCheckNetworkConnection](on-check-network-connection.md): [NetworkConnectionInterceptor.OnCheckNetworkConnection](-on-check-network-connection/index.md) |

## Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | [androidJvm]<br>open override fun [intercept](intercept.md)(chain: Interceptor.Chain): Response |
