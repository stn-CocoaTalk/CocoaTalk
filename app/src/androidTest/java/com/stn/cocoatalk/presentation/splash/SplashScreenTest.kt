package com.stn.cocoatalk.presentation.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import com.stn.cocoatalk.presentation.MainActivity
import com.stn.cocoatalk.ui.theme.CocoaTalkAppTheme
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @RelaxedMockK
    lateinit var navController: NavController

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun splashScreen_displaysAndDisappears() = runBlockingTest {
        composeTestRule.setContent {
            CocoaTalkAppTheme {

            }
        }
    }
}