package com.stn.cocoatalk.data.remote

import com.stn.cocoatalk.data.remote.dto.MessageDto
import com.stn.cocoatalk.domain.model.Message
import io.ktor.client.*
import io.ktor.client.request.*

class MessageServiceImpl(
    private val client: HttpClient
): MessageService {

    override suspend fun getAllMessages(sender: String, receiver: String): List<Message> {
        return try {
            client.get<List<MessageDto>>(MessageService.Endpoints.GetAllMessages.url +
                    "?sender=" + sender + "&receiver=" + receiver)
                .map { it.toMessage() }
        } catch (e: Exception) {
            emptyList()
        }
    }

}