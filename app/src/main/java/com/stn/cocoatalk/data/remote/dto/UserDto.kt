package com.stn.cocoatalk.data.remote.dto

import com.stn.cocoatalk.domain.model.User
import kotlinx.serialization.Serializable

// Entity, Mapper (Data Layer)
@Serializable
data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val password: String
) {
    fun toUser(): User {
        return User(
            username = username,
            email = email,
            password = password
        )
    }
}

class InvalidUserException(message: String): Exception(message)
