package com.stn.cocoatalk.presentation.chatlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stn.cocoatalk.presentation.util.Screen
import com.stn.cocoatalk.ui.theme.PaddingLarge
import com.stn.cocoatalk.ui.theme.PaddingSmall

@Composable
fun ChatListScreen(
    navController: NavController,
    viewModel: ChatListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.getMessages()
    }

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Text(
            text = "Messages",
            style = MaterialTheme.typography.h1,
            color = Color.White,
            modifier = Modifier
                .align(Start)
                .padding(PaddingLarge)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingSmall)
        ) {
            items(state.messages) { message ->
                ChatListItem(
                    message = message,
                    modifier = Modifier,
                    onClick = {
                        navController.navigate(Screen.ChatScreen.route)
                    }
                )
            }
        }
    }
}