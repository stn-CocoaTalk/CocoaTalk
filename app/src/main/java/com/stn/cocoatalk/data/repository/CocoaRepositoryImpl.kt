package com.stn.cocoatalk.data.repository

import com.stn.cocoatalk.data.remote.LoginService
import com.stn.cocoatalk.data.remote.dto.UserDto
import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.model.User
import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocoaRepositoryImpl @Inject constructor(
    private val loginService: LoginService
): CocoaRepository {
    override suspend fun sendMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun observeMessages(): Flow<Message> {
        TODO("Not yet implemented")
    }

    override suspend fun closeSession() {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }
}