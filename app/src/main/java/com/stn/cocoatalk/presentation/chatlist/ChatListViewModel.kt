package com.stn.cocoatalk.presentation.chatlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.stn.cocoatalk.data.remote.ChatSocketService
import com.stn.cocoatalk.data.remote.MessageService
import com.stn.cocoatalk.domain.usecase.CocoaUseCases
import com.stn.cocoatalk.presentation.util.AppState
import com.stn.cocoatalk.presentation.util.Error
import com.stn.cocoatalk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val useCases: CocoaUseCases
): ViewModel() {

    private val _state = mutableStateOf(AppState())
    val state: State<AppState> = _state

    private val _toast = MutableSharedFlow<String>()
    val toast: SharedFlow<String> = _toast.asSharedFlow()

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
                    _toast.emit(result.message ?: Error.UnknownError.message)
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
            }
        }
    }
}