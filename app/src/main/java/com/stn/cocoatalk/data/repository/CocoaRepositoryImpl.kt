package com.stn.cocoatalk.data.repository

import com.stn.cocoatalk.data.remote.ChatSocketService
import com.stn.cocoatalk.data.remote.LoginService
import com.stn.cocoatalk.data.remote.MessageService
import com.stn.cocoatalk.data.remote.dto.UserDto
import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.model.User
import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocoaRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val messageService: MessageService,
    private val chatSocketService: ChatSocketService
): CocoaRepository {
    override suspend fun initSession(username: String) {
        chatSocketService.initSession(username)
    }

    override suspend fun sendMessage(message: String) {
        chatSocketService.sendMessage(message)
    }

    override fun observeMessages(): Flow<Message> {
        return chatSocketService.observeMessages()
    }

    override suspend fun closeSession() {
        chatSocketService.closeSession()
    }

    override suspend fun verifyCredentials(request: User): Resource<User> {
        TODO("Not yet implemented")
    }

    override suspend fun verityToken(token: String): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun addUser(user: UserDto) {
        loginService.addUser(user)
    }

    override suspend fun getUserByEmail(email: String): UserDto {
        return loginService.getUserByEmail(email)
    }

    override suspend fun getAllMessages(): List<Message> {
        return messageService.getAllMessages()
    }
}