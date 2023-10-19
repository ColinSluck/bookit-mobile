package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Booking(
    val id: Int,
    val bookableId: Int,
    val bookable: Bookable,
    val ownerId: Int,
    val date: String,
    val startTime: String,
    val createdAt: String,
    val updatedAt: String
) {
}