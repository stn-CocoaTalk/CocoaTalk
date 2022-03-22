package com.stn.cocoatalk.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stn.cocoatalk.data.remote.dto.InvalidUserException
import com.stn.cocoatalk.data.remote.dto.UserDto
import com.stn.cocoatalk.domain.usecase.CocoaUseCases
import com.stn.cocoatalk.presentation.util.AppState
import com.stn.cocoatalk.presentation.util.Error
import com.stn.cocoatalk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: CocoaUseCases
): ViewModel() {

    private val _inputText = mutableStateOf("")
    val inputText: State<String> = _inputText

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _toast = MutableSharedFlow<String>()
    val toast: SharedFlow<String> = _toast.asSharedFlow()

    private val _state = mutableStateOf(AppState())
    val state: State<AppState> = _state

    fun verifyEmail(): Boolean {
        return _inputText.value.isNotBlank()
    }

    fun getUserByEmail(email: String) {
        useCases.GetUserByEmail(email).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = AppState(
                        currentUser = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = AppState(
                        isLoading = false
                    )
                    showSnackBar(result.message ?: Error.UnknownError.message)
                }
                is Resource.Loading -> {
                    _state.value = AppState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun signUp() {
        val user = UserDto(
            id = "",
            username = _username.value,
            email = _email.value,
            password = _password.value
        )
        try {
            useCases.AddUser(user).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = AppState(
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = AppState(
                            isLoading = false
                        )
                        showSnackBar(result.message ?: Error.UnknownError.message)
                    }
                    is Resource.Loading -> {
                        _state.value = AppState(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: InvalidUserException) {
            showSnackBar(e.message ?: "")
        }
    }

    fun verifyPassword(): Boolean {
        return _state.value.currentUser?.password.equals(_password.value)
    }

    fun textChange(text: String) {
        _inputText.value = text
    }

    fun emailChange(email: String) {
        _email.value = email
    }

    fun showSnackBar(message: String) {
        viewModelScope.launch {
            _toast.emit(message)
        }
    }

    fun passwordChange(password: String) {
        _password.value = password
    }

    fun usernameChange(username: String) {
        _username.value = username
    }
}