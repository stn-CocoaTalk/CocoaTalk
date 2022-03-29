package com.stn.cocoatalk.domain.usecase

import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val repository: CocoaRepository
) {
    operator fun invoke(message: String): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            repository.sendMessage(message)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}