package com.stn.cocoatalk.presentation.util

sealed class Error(val message: String) {
    object EmailIsEmpty: Error("Please input your email address.")
    object UsernameIsEmpty: Error("Please input your username.")
    object PasswordIsEmpty: Error("Please input your password.")
    object InvalidPassword: Error("Invalid password.")
    object UnknownError: Error("A unknown error occured.")
}
