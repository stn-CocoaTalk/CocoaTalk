package com.stn.cocoatalk.presentation.util

import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.model.User

data class AppState(
    val currentUser: User? = null,
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    var userIsNotExist: Boolean = false,
    var authorized: Boolean = false
) {
    fun userIsNotExist(): Boolean {
        userIsNotExist = (currentUser?.username == "" && currentUser.email == "")
        return userIsNotExist
    }
}
