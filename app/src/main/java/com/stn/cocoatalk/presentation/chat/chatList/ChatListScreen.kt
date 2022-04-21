package com.stn.cocoatalk.presentation.chat.chatList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stn.cocoatalk.presentation.chat.ChatViewModel
import com.stn.cocoatalk.presentation.util.Screen
import com.stn.cocoatalk.ui.theme.PaddingLarge
import com.stn.cocoatalk.ui.theme.PaddingSmall
import com.stn.cocoatalk.util.AppState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ChatListScreen (
    navController: NavController,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val user = AppState.currentUser

    LaunchedEffect(key1 = AppState.authorized) {
        if (AppState.authorized.not()) navController.navigate(Screen.InitialScreen.route)
    }

    LaunchedEffect(key1 = true) {
        if (user != null) {
            viewModel.getAllMessages(user.username)
        }
        viewModel.toastEvent.collectLatest { message ->
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = "New",
                        color = MaterialTheme.colors.primary
                    )
                },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Chating",
                        tint = MaterialTheme.colors.primary
                    )
                },
                onClick = { navController.navigate(Screen.UsernameScreen.route) },
                backgroundColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(16.dp)
            )
        }
    ) {
        Column(
            modifier = Modifier
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
}