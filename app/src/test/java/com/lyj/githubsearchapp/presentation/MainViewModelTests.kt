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

}
