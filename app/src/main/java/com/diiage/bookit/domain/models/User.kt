package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class User (
   val id: Int,
   val email: String,
   val firstName: String,
    val lastName: String,
    val role: String,
   val accessToken: String,
    val refreshToken: String,
) {}