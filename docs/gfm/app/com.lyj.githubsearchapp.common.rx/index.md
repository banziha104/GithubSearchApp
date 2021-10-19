//[app](../../index.md)/[com.lyj.githubsearchapp.common.rx](index.md)

# Package com.lyj.githubsearchapp.common.rx

## Types

| Name | Summary |
|---|---|
| [LifecycleTransformer](-lifecycle-transformer/index.md) | [androidJvm]<br>class [LifecycleTransformer](-lifecycle-transformer/index.md)&lt;[T](-lifecycle-transformer/index.md)&gt;(lifecycleEvent: [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html), lifecycleObserver: Observable&lt;[Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)&gt;) : ObservableTransformer&lt;[T](-lifecycle-transformer/index.md), [T](-lifecycle-transformer/index.md)&gt; , FlowableTransformer&lt;[T](-lifecycle-transformer/index.md), [T](-lifecycle-transformer/index.md)&gt; , SingleTransformer&lt;[T](-lifecycle-transformer/index.md), [T](-lifecycle-transformer/index.md)&gt; , MaybeTransformer&lt;[T](-lifecycle-transformer/index.md), [T](-lifecycle-transformer/index.md)&gt; , CompletableTransformer<br>RxJava의 compose()의 파라미터로 사용되는 객체 [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html) 를 전달 받고, 해당 이벤트가 감지되면 onComplete() 를 호출하도록 하는 객체 |
| [RxLifecycleController](-rx-lifecycle-controller/index.md) | [androidJvm]<br>interface [RxLifecycleController](-rx-lifecycle-controller/index.md) : [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)<br>Disposable 관리를 위해 LifecycleOwner 과 연동을 추상화한 인터페이스 |
| [RxLifecycleObserver](-rx-lifecycle-observer/index.md) | [androidJvm]<br>class [RxLifecycleObserver](-rx-lifecycle-observer/index.md)(lifecycleOwner: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)) : [LifecycleObserver](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleObserver.html)<br>[Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html) 를 감지하고, 해당 이벤트에 맞게 Disposable을 관리하는 객체 |
