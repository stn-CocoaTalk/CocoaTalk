package com.stn.cocoatalk.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stn.cocoatalk.presentation.component.AccentText
import com.stn.cocoatalk.presentation.component.SpanStyleText
import com.stn.cocoatalk.presentation.component.StandardTextField
import com.stn.cocoatalk.presentation.util.Error
import com.stn.cocoatalk.presentation.util.Screen
import com.stn.cocoatalk.ui.theme.PaddingLarge
import com.stn.cocoatalk.ui.theme.PaddingMedium
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InitialScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.toast.collectLatest { message ->
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
            StandardTextField(
                text = viewModel.inputText.value,
                hint = "Email",
                onValueChange = {
                    viewModel.textChange(it)
                }
            )
            Spacer(modifier = Modifier.height(PaddingLarge))
            AccentText(
                text = "Continue",
                onClick = {
                    if(viewModel.verifyEmail()) {
                        navController.navigate("${Screen.LoginScreen.route}/${viewModel.inputText.value}")
                    } else {
                        viewModel.showSnackBar(Error.EmailIsEmpty.message)
                    }
                }
            )
            Spacer(modifier = Modifier.height(PaddingLarge))
            SpanStyleText(
                text = "Don't have an account?",
                clickableText = "Sign Up!",
                onclick = {
                    navController.navigate(Screen.SignupScreen.route)
                }
            )
        }
    }
}