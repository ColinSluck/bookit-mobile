package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.Signup
import com.diiage.bookit.domain.models.User

interface AuthRepository {
    suspend fun login(credentials: Credentials): User?
    suspend fun signup(signup: Signup): User?
    fun logout()
}