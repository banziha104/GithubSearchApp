package com.lyj.githubsearchapp.presentation

import com.lyj.githubsearchapp.TestConfig
import com.lyj.githubsearchapp.presentation.activity.MainViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [TestConfig.SDK_VERSION])
class MainViewModelTests {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val testTextList = listOf<String>("반지하","ban","하지반","지하반","anb","nba")
//    @Inject
//    lateinit var mainViewModel : MainViewModel

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun `초성_분해_테스트`(){

    }
}
