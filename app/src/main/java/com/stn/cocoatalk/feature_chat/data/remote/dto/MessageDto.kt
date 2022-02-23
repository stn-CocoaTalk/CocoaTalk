package com.stn.cocoatalk.feature_chat.data.remote.dto

import com.stn.cocoatalk.feature_chat.domain.model.Message
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class MessageDto(
    val text: String,
    val username: String,
    val timestamp: Long,
    val id: String
) {
    fun toMessage(): Message {
        val date = Date(timestamp)
        val formattedDate = SimpleDateFormat("a h:m")
            .format(date)
        return Message(
            text = text,
            username = username,
            timestamp = formattedDate
        )
    }
}
