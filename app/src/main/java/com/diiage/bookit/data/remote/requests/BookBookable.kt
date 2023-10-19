package com.diiage.bookit.data.remote.requests

import kotlinx.serialization.Serializable

/**
 * Represents a data transfer object (DTO) to request the booking of a bookable item.
 *
 * @property date The specific date for which the bookable item is being booked.
 * @property slotId The unique identifier of the slot during which the bookable item is to be reserved.
 */
@Serializable
data class BookBookable (
    val date: String,
    val slotId: Int,
)