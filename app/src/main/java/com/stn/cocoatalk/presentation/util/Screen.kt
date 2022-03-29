package com.stn.cocoatalk.presentation.util

import android.annotation.SuppressLint

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object InitialScreen: Screen("initial_screen")
    object SignupScreen: Screen("signup_screen")
    object LoginScreen: Screen("login_screen")
    object ChatListScreen: Screen("chat_list_screen")
    object UsernameScreen: Screen("username_screen")
    object ChatScreen: Screen("chat_screen")
}
