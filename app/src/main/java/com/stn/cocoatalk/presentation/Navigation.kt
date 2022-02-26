package com.stn.cocoatalk.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stn.cocoatalk.presentation.initial.InitialScreen
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

        }
        composable(Screen.LoginScreen.route) {

        }
        composable(Screen.ChatListScreen.route) {

        }
        composable(Screen.ChatScreen.route) {

        }
    }
}