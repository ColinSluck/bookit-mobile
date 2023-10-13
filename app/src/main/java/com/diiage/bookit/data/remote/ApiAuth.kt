package com.diiage.bookit.data.remote

import com.diiage.bookit.domain.repositories.PreferenceRepository
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

class ApiAuth(
    private val preferenceRepository: PreferenceRepository
) {
    private val apiUrl = Url.BaseUrl.path

    private val client = createHttpClient(apiUrl)


    suspend inline fun <reified T> get(path: String, queryParams: StringValues? = null): T {
        return createRequest(path, HttpMethod.Get, queryParams).body<T>();
    }

    suspend inline fun <reified T> post(path: String, body: Any): T {
        return createRequest(path, HttpMethod.Post, body = body).body<T>();
    }

    suspend inline fun <reified T> put(path: String, body: Any): T {
        return createRequest(path, HttpMethod.Put, body = body).body<T>();
    }

    suspend inline fun <reified T> delete(path: String): T {
        return createRequest(path, HttpMethod.Delete).body<T>();
    }

    suspend fun createRequest(path: String, httpMethod: HttpMethod, queryParams: StringValues? =  null, body: Any? = null): HttpResponse {
        val request: HttpRequestBuilder = HttpRequestBuilder()

        request.urlBuilder(path, queryParams)

        request.method = httpMethod
        request.contentType(ContentType.Application.Json)

        if(body != null) request.setBody(body)

        request.authenticationHeader()

        try {
            return client.request(request);
        } catch (e: Exception) {
            throw e
        }
    }

    private fun HttpRequestBuilder.authenticationHeader() {
        headers.append("Authorization", "Bearer ${preferenceRepository.get(Preferences.AccessToken.name)}")
    }

    private fun HttpRequestBuilder.urlBuilder(path: String, queryParams: StringValues? = null) {
        url.takeFrom("${apiUrl}${path}")

        if(queryParams != null) url.parameters.appendAll(queryParams)
    }
}
