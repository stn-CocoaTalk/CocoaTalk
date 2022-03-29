package com.stn.cocoatalk.domain.usecase

data class CocoaUseCases(
    val GetUserByEmail: GetUserByEmail,
    val AddUser: AddUser,
    val GetAllMessages: GetAllMessages,
    val CloseSession: CloseSession,
    val SendMessage: SendMessage,
    val InitSession: InitSession,
    val ObserveMessages: ObserveMessages
)
