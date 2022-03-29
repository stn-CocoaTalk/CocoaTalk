package com.stn.cocoatalk.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stn.cocoatalk.presentation.component.AccentText
import com.stn.cocoatalk.presentation.component.LabelTextField
import com.stn.cocoatalk.presentation.component.SpanStyleText
import com.stn.cocoatalk.presentation.component.StandardTextField
import com.stn.cocoatalk.presentation.util.Screen
import com.stn.cocoatalk.ui.theme.PaddingMedium
import com.stn.cocoatalk.ui.theme.PaddingSmall
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.toast.collectLatest { message ->
            if (message == "Success!") {
                val result = scaffoldState.snackbarHostState.showSnackbar(
                    message,
                    actionLabel = "Sign In!"
                )
                if (result == SnackbarResult.ActionPerformed) {
                    navController.navigateUp()
                }
            } else {
                scaffoldState.snackbarHostState.showSnackbar(message)
            }
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
            LabelTextField(
                text = viewModel.username.value,
                label = "Username",
                onValueChange = {
                    viewModel.usernameChange(it)
                }
            )
            Spacer(modifier = Modifier.padding(PaddingSmall))
            LabelTextField(
                text = viewModel.email.value,
                label = "Email",
                onValueChange = {
                    viewModel.emailChange(it)
                }
            )
            Spacer(modifier = Modifier.padding(PaddingSmall))
            StandardTextField(
                text = viewModel.password.value,
                hint = "Password",
                onValueChange = {
                    viewModel.passwordChange(it)
                },
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.padding(PaddingSmall))
            SpanStyleText(
                text = "Already have an account?",
                clickableText = "Sign In!",
                onclick = {
                    navController.popBackStack()
                    navController.navigate(Screen.InitialScreen.route)
                }
            )
            Spacer(modifier = Modifier.padding(PaddingSmall))
            AccentText(
                text = "Continue",
                onClick = {
                    viewModel.signUp()
                }
            )
        }
    }
}