package com.stn.cocoatalk.presentation.login

import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.model.User

data class LoginState(
    val currentUser: User? = null,
    val isLoading: Boolean = false,
    var userIsNotExist: Boolean = false,
) {
    fun userIsNotExist(): Boolean {
        userIsNotExist = (currentUser?.username == "" && currentUser.email == "")
        return userIsNotExist
    }
}
