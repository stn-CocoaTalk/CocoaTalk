package com.stn.cocoatalk.domain.repository

import com.stn.cocoatalk.data.remote.dto.UserDto
import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.model.User
import com.stn.cocoatalk.util.Resource
import kotlinx.coroutines.flow.Flow

interface CocoaRepository {

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    suspend fun verifyCredentials(request: User): Resource<User>

    suspend fun verityToken(token: String): Resource<Unit>

    suspend fun addUser(user: UserDto)

    suspend fun getUserByEmail(email: String): UserDto

    suspend fun getAllMessages(): List<Message>
}