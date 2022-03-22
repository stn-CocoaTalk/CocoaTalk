package com.stn.cocoatalk.domain.usecase

import com.stn.cocoatalk.domain.model.User
import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.presentation.util.Error
import com.stn.cocoatalk.util.Resource
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserByEmail @Inject constructor(
    private val repository: CocoaRepository
) {
    operator fun invoke(email: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getUserByEmail(email)
            val user = data.toUser()
            emit(Resource.Success(user))
        } catch (e: RedirectResponseException) {
            emit(Resource.Error(e.response.status.description))
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.response.status.description))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.response.status.description))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}