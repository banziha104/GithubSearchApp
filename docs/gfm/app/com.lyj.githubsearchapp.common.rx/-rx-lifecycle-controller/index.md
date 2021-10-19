//[app](../../../index.md)/[com.lyj.githubsearchapp.common.rx](../index.md)/[RxLifecycleController](index.md)

# RxLifecycleController

[androidJvm]\
interface [RxLifecycleController](index.md) : [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)

Disposable 관리를 위해 LifecycleOwner 과 연동을 추상화한 인터페이스

## Properties

| Name | Summary |
|---|---|
| [rxLifecycleObserver](rx-lifecycle-observer.md) | [androidJvm]<br>abstract val [rxLifecycleObserver](rx-lifecycle-observer.md): [RxLifecycleObserver](../-rx-lifecycle-observer/index.md) |

## Functions

| Name | Summary |
|---|---|
| [add](add.md) | [androidJvm]<br>open fun [add](add.md)(disposable: Disposable, lifecycleEvent: [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)) |
| [disposeByOnDestroy](dispose-by-on-destroy.md) | [androidJvm]<br>open fun &lt;[T](dispose-by-on-destroy.md)&gt; [disposeByOnDestroy](dispose-by-on-destroy.md)(): [LifecycleTransformer](../-lifecycle-transformer/index.md)&lt;[T](dispose-by-on-destroy.md)&gt; |
| [disposeByOnPause](dispose-by-on-pause.md) | [androidJvm]<br>open fun &lt;[T](dispose-by-on-pause.md)&gt; [disposeByOnPause](dispose-by-on-pause.md)(): [LifecycleTransformer](../-lifecycle-transformer/index.md)&lt;[T](dispose-by-on-pause.md)&gt; |
| [disposeByOnStop](dispose-by-on-stop.md) | [androidJvm]<br>open fun &lt;[T](dispose-by-on-stop.md)&gt; [disposeByOnStop](dispose-by-on-stop.md)(): [LifecycleTransformer](../-lifecycle-transformer/index.md)&lt;[T](dispose-by-on-stop.md)&gt; |

## Inherited functions

| Name | Summary |
|---|---|
| [getLifecycle](index.md#1810192813%2FFunctions%2F-912451524) | [androidJvm]<br>@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)<br>abstract fun [getLifecycle](index.md#1810192813%2FFunctions%2F-912451524)(): [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html) |

## Inheritors

| Name |
|---|
| [MainActivity](../../com.lyj.githubsearchapp.presentation.activity/-main-activity/index.md) |
