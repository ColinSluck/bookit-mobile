package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.API
import com.diiage.bookit.domain.exceptions.LoginException
import com.diiage.bookit.domain.exceptions.SignupException
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.Signup
import com.diiage.bookit.domain.models.User
import com.diiage.bookit.domain.repositories.AuthRepository
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.functions.isValidLoginForm
import com.diiage.bookit.ui.core.functions.isValidSignupForm
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthRepositoryImpl(private val api : API, private val preferenceRepository: PreferenceRepository) : AuthRepository {
    override suspend fun login(credentials: Credentials): User {
        if(!isValidLoginForm(credentials)) throw LoginException.ValidationError;

        val user = api.login(credentials) ?: throw LoginException.IncorrectPassword

        preferenceRepository.save("access_token", user.accessToken)
        preferenceRepository.save("refresh_token", user.refreshToken)

        val userString = Json.encodeToString(user)
        preferenceRepository.save("user", userString)

        return user
    }

    override suspend fun signup(signup: Signup): User {
        if(!isValidSignupForm(signup)) throw SignupException.ValidationError;

        val user = api.signup(signup) ?: throw SignupException.SignupError;

        preferenceRepository.save("access_token", user.accessToken)
        preferenceRepository.save("refresh_token", user.refreshToken)

        val userString = Json.encodeToString(user)
        preferenceRepository.save("user", userString)

        return user;
    }

    override fun logout() {
        preferenceRepository.remove("access_token")
        preferenceRepository.remove("refresh_token")
        preferenceRepository.remove("user")
    }
}