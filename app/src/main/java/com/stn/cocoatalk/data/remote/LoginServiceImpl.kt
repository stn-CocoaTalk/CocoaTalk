package com.stn.cocoatalk.data.remote

import com.stn.cocoatalk.data.remote.dto.UserDto
import com.stn.cocoatalk.domain.model.User
import com.stn.cocoatalk.util.Resource
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.auth.AuthScheme.Bearer
import io.ktor.http.cio.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import javax.inject.Inject

class LoginServiceImpl @Inject constructor(
    private val client: HttpClient
    ): LoginService {

    override suspend fun getUserByEmail(email: String): UserDto {
        val request = UserDto(
            id = "",
            username = "",
            email = email,
            password = ""
        )
        return client.post {
            url(LoginService.Endpoints.GetUserByEmail.url)
            contentType(ContentType.Application.Json)
            body = request
        }
    }

    override suspend fun addUser(user: UserDto) {
        client.post<Unit> {
            url(LoginService.Endpoints.AddUser.url)
            contentType(ContentType.Application.Json)
            body = user
        }
    }
}