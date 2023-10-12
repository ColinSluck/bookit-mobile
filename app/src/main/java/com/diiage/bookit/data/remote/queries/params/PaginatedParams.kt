package com.diiage.bookit.data.remote.queries.params

import io.ktor.util.*
import kotlin.reflect.KProperty1

data class PaginatedParams (
    val page: Int,
    val limit: Int
){
    fun toStringValues(): StringValues {
        return StringValues.build {
            this@PaginatedParams::class.members.forEach {
                if (it is KProperty1<*, *>) {
                    val value = it.call(this@PaginatedParams)
                    if (value != null) {
                        append(it.name, value.toString())
                    }
                }
            }
        }
    }
}