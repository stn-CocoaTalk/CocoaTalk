package com.stn.cocoatalk.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stn.cocoatalk.data.remote.ChatSocketService
import com.stn.cocoatalk.data.remote.MessageService
import com.stn.cocoatalk.domain.usecase.CocoaUseCases
import com.stn.cocoatalk.presentation.util.AppState
import com.stn.cocoatalk.presentation.util.Error
import com.stn.cocoatalk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCases: CocoaUseCases
): ViewModel() {

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    private val _state = mutableStateOf(AppState())
    val state: State<AppState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun connectToChat(username: String) {
        getAllMessages()
        useCases.InitSession(username).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    useCases.ObserveMessages().onEach { message ->
                        val newList = state.value.messages.toMutableList().apply {
                            add(0, message)
                        }
                        _state.value = state.value.copy(
                            messages = newList
                        )
                    }.launchIn(viewModelScope)
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _toastEvent.emit(result.message ?: Error.UnknownError.message)
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
            }
        }
    }

    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun disconnect() {
        useCases.CloseSession()
    }

    fun getAllMessages() {
        useCases.GetAllMessages().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        messages = result.data!!
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _toastEvent.emit(result.message ?: Error.UnknownError.message)
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
            }
        }
    }

    fun sendMessage() {
        viewModelScope.launch {
            if(messageText.value.isNotBlank()) {
                useCases.SendMessage(messageText.value)
                _messageText.value = ""
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}