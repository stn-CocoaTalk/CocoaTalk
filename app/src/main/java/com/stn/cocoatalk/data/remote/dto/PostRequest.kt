package com.stn.cocoatalk.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val username: String,
    val email: String,
    val password: String
)
