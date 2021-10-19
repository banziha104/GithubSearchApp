package com.lyj.githubsearchapp.utils

import com.lyj.githubsearchapp.common.utils.InitialSoundNotCorrectedException
import com.lyj.githubsearchapp.common.utils.KoreanLanguageUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class KoreanUtilsTests{
    private val correctTextList = listOf<Pair<Char,String>>(
        'ㅂ' to "반지하",
        'b' to "ban",
        'ㅎ' to "하지반",
        'ㅈ' to "지하반",
        'a' to "anb",
        'n' to "nba"
    )

    private val incrrectText = "ㅏ에이오우"


    @Test
    fun `초성_분해_테스트_옳은_예`(){
        correctTextList
            .forEach { (char, str)->
                try {
                    val (i, s) = KoreanLanguageUtils.splitInitialSound(str)
                    println("$i $s")
                    assert(i == char)
                }catch (e : InitialSoundNotCorrectedException){
                    assert(false)
                }
            }
    }
    @Test
    fun `초성_분해_테스트_틀린_예`(){
        try {
            KoreanLanguageUtils.splitInitialSound(incrrectText)
            assert(false)
        }catch (e : InitialSoundNotCorrectedException){
            assert(true)
        }
    }

}