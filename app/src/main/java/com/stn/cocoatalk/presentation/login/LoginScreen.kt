package com.stn.cocoatalk.presentation.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.stn.cocoatalk.presentation.component.AccentText
import com.stn.cocoatalk.presentation.component.StandardTextField
import com.stn.cocoatalk.ui.theme.PaddingMedium
import com.stn.cocoatalk.ui.theme.PaddingSmall
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val email = navBackStackEntry.arguments?.getString("current_user_email")

    LaunchedEffect(key1 = true) {
        viewModel.state.collectLatest { message ->
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = PaddingMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(text = "Username")
                Spacer(modifier = Modifier.height(PaddingSmall))
                Text(
                    text = "$email",
                    style = TextStyle(textDecoration = TextDecoration.Underline)
                )
            }
            Spacer(modifier = Modifier.height(PaddingSmall))
            StandardTextField(
                text = viewModel.password.value,
                hint = "Password",
                onValueChange = {
                    viewModel.passwordChange(it)
                },
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(PaddingSmall))
            AccentText(
                text = "Continue",
                onClick = {
                    viewModel.login(email as String)
                }
            )
        }
    }
}