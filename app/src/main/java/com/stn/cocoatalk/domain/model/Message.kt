package com.stn.cocoatalk.domain.model

data class Message(
    val text: String,
    val sendUser: User,
    val receiveUser: User,
    val timestamp: String
)
