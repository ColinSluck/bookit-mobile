package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.createHttpClient
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.User
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

const val API_URL = "http://10.4.0.100:45455"

class API() {

    private val client = createHttpClient(API_URL)

    suspend fun login(credentials: Credentials): User {
        try {
            val response: HttpResponse = client.post("/api/auth/login"){
                contentType(ContentType.Application.Json)
                setBody(credentials)
            }

            return response.body<User>();

        } catch (e: Exception) {
            throw e
        }
    }

}


