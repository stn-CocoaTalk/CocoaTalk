package com.stn.cocoatalk.data.remote.dto

import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.model.User
import com.stn.cocoatalk.util.AppState
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

// Mapper (Data Layer)
// Convert ext data to our data for using application.
@Serializable
data class MessageDto(
    val text: String,
    val sendUserEmail: String,
    val sendUserName: String,
    val receiveUserEmail: String,
    val receiveUserName: String,
    val timestamp: Long,
    val id: String
) {
    fun toMessage(): Message {
        val date = Date(timestamp)
        val formattedDate = SimpleDateFormat("a h:m")
            .format(date)
        return Message(
            text = text,
            sendUser = AppState.currentUser
                ?: User(
                    email = sendUserEmail,
                    username = sendUserName,
                    password = ""
                ),
            receiveUser = User(
                email = receiveUserEmail,
                username = receiveUserName,
                password = ""
            ),
            timestamp = formattedDate
        )
    }
}
