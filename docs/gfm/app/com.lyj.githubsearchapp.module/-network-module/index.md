//[app](../../../index.md)/[com.lyj.githubsearchapp.module](../index.md)/[NetworkModule](index.md)

# NetworkModule

[androidJvm]\
@Module

class [NetworkModule](index.md)

## Functions

| Name | Summary |
|---|---|
| [provideCallAdapterFactory](provide-call-adapter-factory.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [provideCallAdapterFactory](provide-call-adapter-factory.md)(): CallAdapter.Factory |
| [providerConvertFactory](provider-convert-factory.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [providerConvertFactory](provider-convert-factory.md)(): Converter.Factory |
| [providerOkHttpClient](provider-ok-http-client.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [providerOkHttpClient](provider-ok-http-client.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): OkHttpClient |
| [provideServiceGenerator](provide-service-generator.md) | [androidJvm]<br>@Provides<br>@Singleton<br>fun [provideServiceGenerator](provide-service-generator.md)(client: OkHttpClient, callAdapter: CallAdapter.Factory, converter: Converter.Factory): [ServiceGenerator](../../com.lyj.githubsearchapp.data.source.remote/-service-generator/index.md) |
