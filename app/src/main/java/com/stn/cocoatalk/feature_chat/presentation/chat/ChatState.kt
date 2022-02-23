package com.stn.cocoatalk.feature_chat.presentation.chat

import com.stn.cocoatalk.feature_chat.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
