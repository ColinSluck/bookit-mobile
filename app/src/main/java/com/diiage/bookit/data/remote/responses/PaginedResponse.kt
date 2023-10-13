package com.diiage.bookit.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class PaginatedResponse<T>(
    val data: List<T>,
    val totalCount: Int,
)