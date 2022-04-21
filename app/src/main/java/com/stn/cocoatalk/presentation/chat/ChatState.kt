package com.stn.cocoatalk.presentation.chat

import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.model.User

data class ChatState(
    val currentUser: User? = null,
    val otherUser: User? = null,
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
