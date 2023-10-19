package com.diiage.bookit.data.remote

import android.util.Log
import com.diiage.bookit.domain.exceptions.LoginException
import com.diiage.bookit.domain.exceptions.SignupException
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.Signup
import com.diiage.bookit.domain.models.User
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

/**
 * This class provides methods for API calls related to authentication.
 */
class API() {

    // HttpClient instance with the base URL.
    private val client = createHttpClient(Url.BaseUrl.path)

    /**
     * Tries to authenticate the user with the given [credentials].
     *
     * @param credentials The user's login information.
     * @return A [User] object if login is successful, null otherwise.
     * @throws LoginException.ServerError If there's an error during the login process.
     */
    suspend fun login(credentials: Credentials): User? {
        try {
            val response: HttpResponse = client.post(Url.Login.path) {
                contentType(ContentType.Application.Json)
                setBody(credentials)
            }

            if (response.status != HttpStatusCode.OK) {
                return null
            }

            return response.body<User>()
        } catch (e: Exception) {
            Log.e("API", e.message.toString())
            throw LoginException.ServerError
        }
    }

    /**
     * Tries to register a new user with the given [signup] information.
     *
     * @param signup The user's registration information.
     * @return A [User] object if signup is successful, null otherwise.
     * @throws SignupException.ServerError If there's an error during the signup process.
     */
    suspend fun signup(signup: Signup): User? {
        try {
            val response: HttpResponse = client.post(Url.Signup.path) {
                contentType(ContentType.Application.Json)
                setBody(signup)
            }

            if (response.status != HttpStatusCode.Created) {
                return null
            }

            return response.body<User>()
        } catch (e: Exception) {
            Log.e("API", e.message.toString())
            throw SignupException.ServerError
        }
    }
}



