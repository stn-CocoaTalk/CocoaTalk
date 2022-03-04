package com.stn.cocoatalk.data.remote

import com.stn.cocoatalk.data.remote.dto.PostRequest

interface LoginService {

    suspend fun verifyCredentials(request: PostRequest): String?

    suspend fun verityToken(token: String): String?

    suspend fun createUser(request: PostRequest)

    companion object {
        const val BASE_URL = "http://172.30.1.40:8080"
    }

    sealed class Endpoints(val url: String) {
        object VerifyUser: Endpoints("$BASE_URL/login")
        object VerifyToken: Endpoints("$BASE_URL/token")
        object CreateUser: Endpoints("$BASE_URL/signup")
    }
}