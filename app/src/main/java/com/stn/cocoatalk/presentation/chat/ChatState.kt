package com.stn.cocoatalk.presentation.chat

import com.stn.cocoatalk.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
