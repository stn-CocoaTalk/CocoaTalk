package com.stn.cocoatalk.data.remote

import com.stn.cocoatalk.data.remote.dto.PostRequest
import com.stn.cocoatalk.domail.model.User
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.auth.AuthScheme.Bearer
import io.ktor.http.cio.websocket.*

class LoginServiceImpl(
    private val client: HttpClient
): LoginService {
    override suspend fun verifyCredentials(request: PostRequest): String? {
        return try {
            client.post<String> {
                url(LoginService.Endpoints.VerifyUser.url)
                contentType(ContentType.Application.Json)
                body = request
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            ""
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            ""
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            ""
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ""
        }
    }

    override suspend fun verityToken(token: String): String? {
        return try {
            client.request {
                method = HttpMethod.Get
                url(LoginService.Endpoints.VerifyToken.url)
                headers {
                    append(HttpHeaders.Authorization, "$Bearer $token")
                }
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            ""
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            ""
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            ""
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ""
        }
    }

    override suspend fun createUser(request: PostRequest) {
        try {
            client.post<String> {
                url(LoginService.Endpoints.CreateUser.url)
                contentType(ContentType.Application.Json)
                body = request
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}