package com.stn.cocoatalk.data.remote

import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.util.Constants

interface MessageService {

    suspend fun getAllMessages(sender: String, receiver: String): List<Message>

    companion object {
        const val BASE_URL = "http://${Constants.BASE_URL}"
    }

    sealed class Endpoints(val url: String) {
        object GetAllMessages: Endpoints("$BASE_URL/messages")
    }
}