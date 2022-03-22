package com.stn.cocoatalk.data.remote

import com.stn.cocoatalk.data.remote.dto.MessageDto
import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.util.Constants
import com.stn.cocoatalk.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        username: String
    ): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://${Constants.BASE_URL}"
    }

    sealed class Endpoints(val url: String) {
        object ChatSocket: Endpoints("$BASE_URL/chat-socket")
    }
}