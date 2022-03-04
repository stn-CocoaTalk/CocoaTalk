package com.stn.cocoatalk.presentation.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stn.cocoatalk.data.remote.LoginService
import com.stn.cocoatalk.data.remote.dto.PostRequest
import com.stn.cocoatalk.domail.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val loginService: LoginService
): ViewModel() {

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _event = MutableSharedFlow<String>()
    val event: SharedFlow<String> = _event.asSharedFlow()

    fun usernameChange(username: String) {
        _username.value = username
    }

    fun emailChange(email: String) {
        _email.value = email
    }

    fun passwordChange(password: String) {
        _password.value = password
    }

    fun showSnackBar() {
        viewModelScope.launch {
            _event.emit("")
        }
    }

    fun signUp(): String {
        var result: String = "Error"
        viewModelScope.launch {
            val request = PostRequest(
                username = _username.value,
                email = _email.value,
                password = _password.value
            )
            loginService.createUser(request)
        }
        return result
    }
}