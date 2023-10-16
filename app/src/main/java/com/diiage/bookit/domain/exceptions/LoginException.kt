package com.diiage.bookit.domain.exceptions

sealed class LoginException : Exception() {
    object IncorrectPassword : LoginException()
    object ValidationError : LoginException()
    object ServerError : LoginException()
}