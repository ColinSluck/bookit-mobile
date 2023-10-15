package com.diiage.bookit.domain.models

import io.ktor.util.StringValues
import java.time.LocalDate

data class Search(
    val minCapacity: Int?,
    val slotId: Int?,
    val date: LocalDate?,
    val bookableTypeId: Int?,
    val materialIds: List<Int>?,
    val page: Int = 1,
    val limit: Int = 10
) {

    fun toStringValues(): StringValues {
        return StringValues.build {
            minCapacity?.let { append("minCapacity", it.toString()) }
            slotId?.let { append("slotId", it.toString()) }
            date?.let { append("date", it.toString()) }
            bookableTypeId?.let { append("bookableTypeId", it.toString()) }
            materialIds?.takeIf { it.isNotEmpty() }?.let { append("materialIds", it.joinToString(",")) }
            append("offset", ((page - 1) * limit).toString())
            append("limit", limit.toString())
        }
    }


}