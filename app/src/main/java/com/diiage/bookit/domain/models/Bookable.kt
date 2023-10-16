package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Bookable (
    val id: Int,
    val createdAt: String,
    val updatedAt: String,

    val name: String,
    val description: String,
    val place: String,
    val maxCapacity: Int,
    val bookableTypeId: Int,

    val images: List<String>? = null,
)