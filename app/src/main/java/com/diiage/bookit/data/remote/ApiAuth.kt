package com.diiage.bookit.data.remote

import android.net.Uri
import android.content.Context
import com.diiage.bookit.domain.models.UploadableFile
import com.diiage.bookit.domain.repositories.PreferenceRepository
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.PartData
import io.ktor.util.*
import io.ktor.utils.io.core.Input
import io.ktor.utils.io.streams.asInput
import java.io.ByteArrayInputStream
import java.io.InputStream

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

    suspend inline fun <reified T> postWithFiles(path: String, files: List<UploadableFile>? = null): T {
        return createRequestWithFiles(path, HttpMethod.Post, files = files).body<T>();
    }

    suspend fun createRequest(
        path: String,
        httpMethod: HttpMethod,
        queryParams: StringValues? = null,
        body: Any? = null,
    ): HttpResponse {

        val request = HttpRequestBuilder()
        request.urlBuilder(path, queryParams)

        request.method = httpMethod

        request.contentType(ContentType.Application.Json)
        request.setBody(body)

        request.authenticationHeader()

        try {
            return client.request(request)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun createRequestWithFiles(
        path: String,
        httpMethod: HttpMethod,
        files: List<UploadableFile>? = null
    ): HttpResponse {
        val request = HttpRequestBuilder()
        request.urlBuilder(path, null)

        request.method = httpMethod

        if (files != null) {
            val parts = mutableListOf<PartData>()

            files.forEach { file ->
                val byteArray = file.inputStream?.use { it.readBytes() } ?: file.byteArray
                val streamProvider: () -> Input = { byteArray?.inputStream()?.asInput() ?: ByteArrayInputStream(ByteArray(0)).asInput() }

                parts.add(PartData.FileItem(streamProvider, {}, headersOf(
                    HttpHeaders.ContentDisposition,
                    "form-data; name=\"files\"; filename=\"${file.name}\""
                )))
            }

            request.setBody(MultiPartFormDataContent(parts))
        }

        request.authenticationHeader()

        try {
            return client.request(request)
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
