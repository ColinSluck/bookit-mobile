package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Signup(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
) {}