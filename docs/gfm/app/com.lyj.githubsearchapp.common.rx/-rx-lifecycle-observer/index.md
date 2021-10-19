//[app](../../../index.md)/[com.lyj.githubsearchapp.common.rx](../index.md)/[RxLifecycleObserver](index.md)

# RxLifecycleObserver

[androidJvm]\
class [RxLifecycleObserver](index.md)(lifecycleOwner: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)) : [LifecycleObserver](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleObserver.html)

[Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html) 를 감지하고, 해당 이벤트에 맞게 Disposable을 관리하는 객체

## Parameters

androidJvm

| | |
|---|---|
| lifecycleOwner | 관찰할 LifecycleOwner |

## Constructors

| | |
|---|---|
| [RxLifecycleObserver](-rx-lifecycle-observer.md) | [androidJvm]<br>fun [RxLifecycleObserver](-rx-lifecycle-observer.md)(lifecycleOwner: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)) |

## Properties

| Name | Summary |
|---|---|
| [lifecycleOwner](lifecycle-owner.md) | [androidJvm]<br>private val [lifecycleOwner](lifecycle-owner.md): [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html) |
| [onDestroyDisposable](on-destroy-disposable.md) | [androidJvm]<br>private val [onDestroyDisposable](on-destroy-disposable.md): CompositeDisposable |
| [onPauseDisposable](on-pause-disposable.md) | [androidJvm]<br>private val [onPauseDisposable](on-pause-disposable.md): CompositeDisposable |
| [onStopDisposable](on-stop-disposable.md) | [androidJvm]<br>private val [onStopDisposable](on-stop-disposable.md): CompositeDisposable |
| [publisher](publisher.md) | [androidJvm]<br>private val [publisher](publisher.md): BehaviorSubject&lt;[Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [add](add.md) | [androidJvm]<br>fun [add](add.md)(disposable: Disposable, lifecycleEvent: [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)) |
| [any](any.md) | [androidJvm]<br>fun [any](any.md)(owner: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html), event: [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)) |
| [transformer](transformer.md) | [androidJvm]<br>fun &lt;[T](transformer.md)&gt; [transformer](transformer.md)(disposeBy: [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)): [LifecycleTransformer](../-lifecycle-transformer/index.md)&lt;[T](transformer.md)&gt; |
