package com.stn.cocoatalk.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stn.cocoatalk.data.remote.LoginService
import com.stn.cocoatalk.data.remote.dto.PostRequest
import com.stn.cocoatalk.domail.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.statement.*
import io.ktor.http.cio.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginService: LoginService
): ViewModel() {

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _state = MutableSharedFlow<String>()
    val state: SharedFlow<String> = _state.asSharedFlow()

    fun passwordChange(password: String) {
        _password.value = password
    }

    fun login(email: String) {
        viewModelScope.launch {
            val request = PostRequest(
                username = "mh",
                email = email,
                password = _password.value
            )
            val response = loginService.verifyCredentials(request).toString()
            _state.emit(response)
            val valid = loginService.verityToken(response).toString()
            _state.emit(valid)
        }
    }
}