package com.stn.cocoatalk.presentation.username

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stn.cocoatalk.presentation.component.AccentText
import com.stn.cocoatalk.presentation.component.StandardTextField
import com.stn.cocoatalk.ui.theme.PaddingSmall
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UsernameScreen(
    navController: NavController,
    viewModel: UsernameViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.onJoinChat.collectLatest { username ->
            navController.navigate("chat_screen/$username")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StandardTextField(
                text = viewModel.usernameText.value,
                hint = "Enter a username",
                onValueChange = viewModel::onUsernameChange
            )
            Spacer(modifier = Modifier.height(PaddingSmall))
            AccentText(
                text = "Continue",
                onClick = viewModel::onJoinClick
            )
        }
    }
}