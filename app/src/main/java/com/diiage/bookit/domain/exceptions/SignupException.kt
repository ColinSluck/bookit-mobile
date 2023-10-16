package com.diiage.bookit.domain.exceptions

sealed class SignupException : Exception() {
    object SignupError : SignupException()
    object ValidationError : SignupException()
    object ServerError : SignupException()
}