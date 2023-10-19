package com.diiage.bookit.data.remote.response

import com.diiage.bookit.domain.models.Slot
import kotlinx.serialization.Serializable

/**
 * Represents a data transfer object (DTO) that encapsulates the response from the API
 * concerning available slots for a specific bookable item on a given date.
 *
 * @property totalCount The total number of available slots.
 * @property data A list of available [Slot]s.
 * @property date The specific date for which the slots are provided.
 */
@Serializable
internal data class BookableSlots (
    val totalCount: Int,
    val data: List<Slot>,
    val date: String,
)