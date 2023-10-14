package com.diiage.bookit.data.remote

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

class API() {
    private val client = createHttpClient(Url.BaseUrl.path)

    suspend fun login(credentials: Credentials): User? {
        try {
            val response: HttpResponse = client.post(Url.Login.path) {
                contentType(ContentType.Application.Json)
                setBody(credentials)
            }

            if (response.status != HttpStatusCode.OK) {
                return null;
            }

            return response.body<User>()
        } catch (e: Exception) {
            throw LoginException.ServerError;
        }
    }

    suspend fun signup(signup: Signup): User? {
        try {
            val response: HttpResponse = client.post(Url.Signup.path) {
                contentType(ContentType.Application.Json)
                setBody(signup)
            }

            if (response.status != HttpStatusCode.Created) {
                return null;
            }

            return response.body<User>()
        } catch (e: Exception) {
            throw SignupException.ServerError;
        }
    }
}


