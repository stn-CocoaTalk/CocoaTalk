package com.stn.cocoatalk.presentation.login

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.stn.cocoatalk.R
import com.stn.cocoatalk.presentation.component.AccentText
import com.stn.cocoatalk.presentation.component.StandardTextField
import com.stn.cocoatalk.presentation.util.Error
import com.stn.cocoatalk.presentation.util.Screen
import com.stn.cocoatalk.ui.theme.PaddingMedium
import com.stn.cocoatalk.ui.theme.PaddingSmall
import com.stn.cocoatalk.ui.theme.TextWhite
import com.stn.cocoatalk.ui.theme.Typography
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val email = navBackStackEntry.arguments?.getString("current_user_email")

    LaunchedEffect(key1 = true) {
        viewModel.getUserByEmail(email!!)
        viewModel.toast.collectLatest { message ->
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = PaddingMedium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                state.currentUser?.let { user ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        item {
                            Text(
                                text = user.username,
                                style = TextStyle(textDecoration = TextDecoration.Underline),
                                fontWeight = Typography.body2.fontWeight,
                                fontSize = 22.sp,
                                color = TextWhite
                            )
                            Spacer(modifier = Modifier.height(PaddingSmall))
                            Text(
                                text = user.email,
                                style = TextStyle(textDecoration = TextDecoration.Underline),
                                fontWeight = Typography.body2.fontWeight,
                                fontSize = 20.sp,
                                color = TextWhite.copy(alpha = 0.8f)
                            )
                            if (user.email.isNotBlank()) {
                                Spacer(modifier = Modifier.height(PaddingMedium))
                                StandardTextField(
                                    text = viewModel.password.value,
                                    hint = "Password",
                                    onValueChange = viewModel::passwordChange,
                                    keyboardType = KeyboardType.Password
                                )
                                Spacer(modifier = Modifier.height(PaddingMedium))
                                AccentText(
                                    text = "Continue",
                                    onClick = {
                                        if (viewModel.verifyPassword()) {
                                            viewModel.setStateAutorized()
                                            navController.navigate(Screen.ChatListScreen.route)
                                        } else {
                                            viewModel.showSnackBar(Error.InvalidPassword.message)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
            if (state.userIsNotExist){
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_refresh),
                            contentDescription = "Return to previous screen.",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}