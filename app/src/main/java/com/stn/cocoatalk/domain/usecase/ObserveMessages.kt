package com.stn.cocoatalk.domain.usecase

import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ObserveMessages @Inject constructor(
    private val repository: CocoaRepository
) {
    operator fun invoke(): Flow<Message> = flow {

    }
}