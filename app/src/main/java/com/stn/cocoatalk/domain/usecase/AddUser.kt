package com.stn.cocoatalk.domain.usecase

import com.stn.cocoatalk.data.remote.dto.InvalidUserException
import com.stn.cocoatalk.data.remote.dto.UserDto
import com.stn.cocoatalk.domain.model.User
import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.presentation.util.Error
import com.stn.cocoatalk.util.Resource
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddUser @Inject constructor(
    private val repository: CocoaRepository
) {
    @Throws(InvalidUserException::class)
    operator fun invoke(user: UserDto): Flow<Resource<Unit>> = flow {
        try {
            if (user.username.isBlank())
                throw InvalidUserException(Error.UsernameIsEmpty.message)
            if (user.email.isBlank())
                throw InvalidUserException(Error.EmailIsEmpty.message)
            if (user.password.isBlank())
                throw InvalidUserException(Error.PasswordIsEmpty.message)
            emit(Resource.Loading())
            repository.addUser(user)
            emit(Resource.Success(Unit))
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