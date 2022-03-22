package com.stn.cocoatalk.data.remote

import com.stn.cocoatalk.data.remote.dto.UserDto
import com.stn.cocoatalk.domain.model.User
import com.stn.cocoatalk.util.Constants
import com.stn.cocoatalk.util.Resource

interface LoginService {

    suspend fun getUserByEmail(email: String): UserDto
    suspend fun addUser(user: UserDto)

    companion object {
        const val BASE_URL = "http://${Constants.BASE_URL}"
    }

    sealed class Endpoints(val url: String) {
        object GetUserByEmail: Endpoints("$BASE_URL/login")
        object VerifyToken: Endpoints("$BASE_URL/token")
        object AddUser: Endpoints("$BASE_URL/signup")
    }
}