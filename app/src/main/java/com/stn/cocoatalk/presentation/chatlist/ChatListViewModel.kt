package com.stn.cocoatalk.presentation.chatlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.stn.cocoatalk.data.remote.ChatSocketService
import com.stn.cocoatalk.data.remote.MessageService
import com.stn.cocoatalk.presentation.util.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatSocketService: ChatSocketService,
    private val messageService: MessageService
): ViewModel() {
    private val _state = mutableStateOf(AppState())
    val state: State<AppState> = _state

    fun getMessages() {
        _state.value = state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            val result = messageService.getAllMessages()
            _state.value = state.value.copy(
                messages = result,
                isLoading = false
            )
        }
    }
}