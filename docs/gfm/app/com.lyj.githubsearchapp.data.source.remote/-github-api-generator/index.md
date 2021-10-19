//[app](../../../index.md)/[com.lyj.githubsearchapp.data.source.remote](../index.md)/[GithubApiGenerator](index.md)

# GithubApiGenerator

[androidJvm]\
class [GithubApiGenerator](index.md)(client: OkHttpClient, callAdapter: CallAdapter.Factory, converter: Converter.Factory) : [ServiceGenerator](../-service-generator/index.md)

Github 관련 API 서비스를 생성하는 구현 객체

## Constructors

| | |
|---|---|
| [GithubApiGenerator](-github-api-generator.md) | [androidJvm]<br>fun [GithubApiGenerator](-github-api-generator.md)(client: OkHttpClient, callAdapter: CallAdapter.Factory, converter: Converter.Factory) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md)<br>Github 에서 사용 할 상수 |

## Properties

| Name | Summary |
|---|---|
| [callAdapter](call-adapter.md) | [androidJvm]<br>private val [callAdapter](call-adapter.md): CallAdapter.Factory |
| [client](client.md) | [androidJvm]<br>private val [client](client.md): OkHttpClient |
| [converter](converter.md) | [androidJvm]<br>private val [converter](converter.md): Converter.Factory |

## Functions

| Name | Summary |
|---|---|
| [generateService](generate-service.md) | [androidJvm]<br>open override fun &lt;[T](generate-service.md)&gt; [generateService](generate-service.md)(service: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](generate-service.md)&gt;): [T](generate-service.md) |
