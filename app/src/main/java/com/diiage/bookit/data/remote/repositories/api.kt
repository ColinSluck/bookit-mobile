package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.createHttpClient
import com.diiage.bookit.data.remote.setBodyJson
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.User
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse

const val API_URL = "http://10.4.0.100:45455/api"

class API {

    private val client = createHttpClient(API_URL)
    suspend fun login(credentials: Credentials): User {
        val response: HttpResponse = client.post("/auth/login") {
            setBodyJson(credentials)
        }

        return response.body<User>();
    }

}


