package com.diiage.bookit.data.remote.queries.params

import io.ktor.util.*
import kotlin.reflect.KProperty1

data class PaginatedParams (
    val page: Int,
    val limit: Int
){
    fun toStringValues(): StringValues {
        return StringValues.build {
            append("offset", ((page - 1) * limit).toString())
            append("limit", limit.toString())
        }
    }
}