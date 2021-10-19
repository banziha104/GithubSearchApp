//[app](../../../index.md)/[com.lyj.githubsearchapp.common.rx](../index.md)/[LifecycleTransformer](index.md)

# LifecycleTransformer

[androidJvm]\
class [LifecycleTransformer](index.md)&lt;[T](index.md)&gt;(lifecycleEvent: [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html), lifecycleObserver: Observable&lt;[Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)&gt;) : ObservableTransformer&lt;[T](index.md), [T](index.md)&gt; , FlowableTransformer&lt;[T](index.md), [T](index.md)&gt; , SingleTransformer&lt;[T](index.md), [T](index.md)&gt; , MaybeTransformer&lt;[T](index.md), [T](index.md)&gt; , CompletableTransformer

RxJava의 compose()의 파라미터로 사용되는 객체 [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html) 를 전달 받고, 해당 이벤트가 감지되면 onComplete() 를 호출하도록 하는 객체

## Parameters

androidJvm

| | |
|---|---|
| lifecycleEvent | onComplete()를 호출할 이벤트 |
| lifecycleObserver | Lifecycle 을 관찰하는 객체 [RxLifecycleObserver.publisher](../-rx-lifecycle-observer/publisher.md) |

## Constructors

| | |
|---|---|
| [LifecycleTransformer](-lifecycle-transformer.md) | [androidJvm]<br>fun [LifecycleTransformer](-lifecycle-transformer.md)(lifecycleEvent: [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html), lifecycleObserver: Observable&lt;[Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [lifecycleEvent](lifecycle-event.md) | [androidJvm]<br>private val [lifecycleEvent](lifecycle-event.md): [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html) |
| [observable](observable.md) | [androidJvm]<br>private val [observable](observable.md): @NonNullObservable&lt;[Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [apply](apply.md) | [androidJvm]<br>open override fun [apply](apply.md)(upstream: Completable): CompletableSource<br>open override fun [apply](apply.md)(upstream: Flowable&lt;[T](index.md)&gt;): Publisher&lt;[T](index.md)&gt;<br>open override fun [apply](apply.md)(upstream: Maybe&lt;[T](index.md)&gt;): MaybeSource&lt;[T](index.md)&gt;<br>open override fun [apply](apply.md)(upstream: Observable&lt;[T](index.md)&gt;): ObservableSource&lt;[T](index.md)&gt;<br>open override fun [apply](apply.md)(upstream: Single&lt;[T](index.md)&gt;): SingleSource&lt;[T](index.md)&gt; |
