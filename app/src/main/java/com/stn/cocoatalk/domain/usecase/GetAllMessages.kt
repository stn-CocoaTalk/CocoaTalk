package com.stn.cocoatalk.domain.usecase

import com.stn.cocoatalk.data.remote.dto.MessageDto
import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllMessages @Inject constructor(
    private val repository: CocoaRepository
) {
    operator fun invoke(sender: String, receiver: String): Flow<Resource<List<Message>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getAllMessages(sender, receiver)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}