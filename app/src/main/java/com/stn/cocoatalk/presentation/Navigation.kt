package com.stn.cocoatalk.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stn.cocoatalk.presentation.chat.ChatScreen
import com.stn.cocoatalk.presentation.chatlist.ChatListScreen
import com.stn.cocoatalk.presentation.login.InitialScreen
import com.stn.cocoatalk.presentation.login.LoginScreen
import com.stn.cocoatalk.presentation.login.SignUpScreen
import com.stn.cocoatalk.presentation.splash.SplashScreen
import com.stn.cocoatalk.presentation.util.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.InitialScreen.route) {
            InitialScreen(navController)
        }
        composable(Screen.SignupScreen.route) {
            SignUpScreen(navController)
        }
        composable(
            route = "${Screen.LoginScreen.route}/{current_user_email}",
            arguments = listOf(navArgument("current_user_email") { NavType.StringType })
        ) {
            LoginScreen(navController, it)
        }
        composable(Screen.ChatListScreen.route) {
            ChatListScreen(navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(username = "TEST")
        }
    }
}