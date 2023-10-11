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

const val API_URL = "http://172.20.10.3:45455/api"

class API() {

    private val client = createHttpClient(API_URL)

    suspend fun login(credentials: Credentials): User {

        val response: HttpResponse = client.post("/auth/login"){
            contentType(ContentType.Application.Json)
            setBody(credentials)
        }

        return response.body<User>();
    }

}


