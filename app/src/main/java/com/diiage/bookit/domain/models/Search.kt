package com.diiage.bookit.domain.models

import io.ktor.util.StringValues
import kotlinx.serialization.Serializable

@Serializable
data class Search(
    val minCapacity: Int? = null,
    val slotId: Int? = null,
    val date: String? = null,
    val bookableTypeId: Int? = null,
    val materialIds: List<Int>? = null,
    val page: Int = 1,
    val limit: Int = 10
) {

    fun toStringValues(): StringValues {
        return StringValues.build {
            minCapacity?.let { append("minCapacity", it.toString()) }
            slotId?.let { append("slotId", it.toString()) }
            date?.let { append("date", it) }
            bookableTypeId?.let { append("bookableTypeId", it.toString()) }
            materialIds?.takeIf { it.isNotEmpty() }?.let { append("materialIds", it.joinToString(",")) }
            append("offset", ((page - 1) * limit).toString())
            append("limit", limit.toString())
        }
    }
    override fun toString(): String {
        val values = toStringValues()
        return "search?" + values.entries().joinToString("&") { "${it.key}=${it.value.joinToString(",")}" }
    }
}