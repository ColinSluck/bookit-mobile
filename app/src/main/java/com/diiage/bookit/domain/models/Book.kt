package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

/**
 * Represents a booking entity, detailing the relationship between a bookable item, a booking, and a slot.
 *
 * @property bookableId The unique identifier of the bookable item being booked.
 * @property bookingId The unique identifier of the booking instance.
 * @property slotId The unique identifier of the slot during which the bookable item is reserved.
 */
@Serializable
data class Book (
    val bookableId: Int,
    val bookingId: Int,
    val slotId: Int,
)
