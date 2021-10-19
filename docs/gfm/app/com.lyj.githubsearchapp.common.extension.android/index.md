//[app](../../index.md)/[com.lyj.githubsearchapp.common.extension.android](index.md)

# Package com.lyj.githubsearchapp.common.extension.android

## Types

| Name | Summary |
|---|---|
| [TabLayoutEventType](-tab-layout-event-type/index.md) | [androidJvm]<br>enum [TabLayoutEventType](-tab-layout-event-type/index.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?, text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[TabLayoutEventType](-tab-layout-event-type/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [searchButtonActionObserver](search-button-action-observer.md) | [androidJvm]<br>fun [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html).[searchButtonActionObserver](search-button-action-observer.md)(): Observable&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt; |
| [selectedObserver](selected-observer.md) | [androidJvm]<br>fun TabLayout.[selectedObserver](selected-observer.md)(defaultPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null): Observable&lt;[TabLayoutEventType](-tab-layout-event-type/index.md)&gt; |
