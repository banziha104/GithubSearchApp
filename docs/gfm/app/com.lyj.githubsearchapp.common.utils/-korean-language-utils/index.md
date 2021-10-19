//[app](../../../index.md)/[com.lyj.githubsearchapp.common.utils](../index.md)/[KoreanLanguageUtils](index.md)

# KoreanLanguageUtils

[androidJvm]\
object [KoreanLanguageUtils](index.md)

한글 관련 Utils

## Properties

| Name | Summary |
|---|---|
| [initialList](initial-list.md) | [androidJvm]<br>private val [initialList](initial-list.md): [CharArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-array/index.html) |
| [koreanRegex](korean-regex.md) | [androidJvm]<br>private val [koreanRegex](korean-regex.md): [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html) |

## Functions

| Name | Summary |
|---|---|
| [isKoreanText](is-korean-text.md) | [androidJvm]<br>private fun [isKoreanText](is-korean-text.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [splitInitialSound](split-initial-sound.md) | [androidJvm]<br>fun [splitInitialSound](split-initial-sound.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>초성, 중성, 종성 분리 메소드 한글의 경우 |
