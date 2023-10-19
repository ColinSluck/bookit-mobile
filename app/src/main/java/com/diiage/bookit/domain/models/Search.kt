package com.diiage.bookit.domain.models

import io.ktor.util.StringValues
import kotlinx.serialization.Serializable

/**
 * This data class, Search, represents a set of search criteria used to filter bookable items.
 *
 * @param minCapacity The minimum capacity required for bookable items.
 * @param slotId The ID of the slot associated with bookable items.
 * @param date The date for which bookable items are being searched.
 * @param bookableTypeId The ID of the bookable type.
 * @param materialIds A list of material IDs associated with bookable items.
 * @param page The page number of results to retrieve (default is 1).
 * @param limit The maximum number of results to return per page (default is 10).
 */
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