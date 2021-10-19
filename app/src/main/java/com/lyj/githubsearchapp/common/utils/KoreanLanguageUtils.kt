package com.lyj.githubsearchapp.common.utils

/**
 * 한글 관련 Utils
 */
object KoreanLanguageUtils{
    // 한글로 시작하는 지 알아보는 정규 표현식
    private val koreanRegex: Regex by lazy {
        Regex("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$")
    }

    private val initialList = charArrayOf(
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ',
        'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    )

    /**
     * 초성, 중성, 종성 분리 메소드
     * 한글의 경우
     * @param text : 추출하고자하는 글자
     * @return char : 초성 , string : 원본
     */
    @Throws(InitialSoundNotCorrectedException::class)
    fun splitInitialSound(text: String): Pair<Char, String> =
        if (isKoreanText(text)) {
            text.first().let { char ->
                // 초성 구하는 공식 (((Initial - 0xAC00) / 28) / 21)
                var firstCharacter = char.code - 0xAC00 // 0xAC00 -> 유니코드 상의 한글의 첫글자('가'의 인덱스)
                if (firstCharacter < 0) throw InitialSoundNotCorrectedException() // ㅏ 와 같이 모음으로 시작하는 경우, '가'보다 유니코드 상에 앞에 있음으로 에러
                firstCharacter /= 28 // 종성으로 나눔
                firstCharacter /= 21 // 중성으로 나눔
                initialList[firstCharacter] to text
            }
        } else {
            text.first() to text
        }
    private fun isKoreanText(text: String): Boolean = koreanRegex.find(text) != null
}

// 모음으로 시작하는 경우 등  한글인데 초성이 없는 경우
class InitialSoundNotCorrectedException : Exception("first character is not correct")