package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.Signup
import com.diiage.bookit.domain.models.User

/**
 * Interface representing authentication operations.
 */
interface AuthRepository {
    /**
     * Authenticates a user with provided credentials.
     *
     * @param credentials The user's login credentials.
     * @return Authenticated user.
     * @throws LoginException.ValidationError If the login form is not valid.
     * @throws LoginException.IncorrectPassword If the provided password is incorrect.
     */
    suspend fun login(credentials: Credentials): User

    /**
     * Registers a new user.
     *
     * @param signup The user's signup information.
     * @return Registered user.
     * @throws SignupException.ValidationError If the signup form is not valid.
     * @throws SignupException.SignupError If there is an error during signup.
     */
    suspend fun signup(signup: Signup): User

    /**
     * Logs out the currently authenticated user.
     */
    fun logout()
}


