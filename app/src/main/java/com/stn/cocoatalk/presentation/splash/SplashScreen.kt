package com.stn.cocoatalk.presentation.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.core.animation.OvershootInterpolator
import androidx.navigation.NavController
import com.stn.cocoatalk.R
import com.stn.cocoatalk.presentation.util.Screen
import com.stn.cocoatalk.util.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    val scale = remember {
        Animatable(0f)
    }
    val over = OvershootInterpolator(2f)

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 50,
                easing = Easing {
                    over.getInterpolation(it)
                }
            )
        )
        delay(Constants.SPLASH_SCREEN_DELAY)
        navController.popBackStack()
        navController.navigate(Screen.InitialScreen.route)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_icons8_cocoa_64),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
        )
    }
}