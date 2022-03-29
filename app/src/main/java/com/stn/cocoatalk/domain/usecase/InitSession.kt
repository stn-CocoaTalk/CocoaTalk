package com.stn.cocoatalk.domain.usecase

import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InitSession @Inject constructor(
    private val repository: CocoaRepository
) {
    operator fun invoke(username: String): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            repository.initSession(username)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}