package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable
/**
 * This data class, Slot, represents a time slot used in the context of booking activities.
 *
 * @param id The unique identifier of the time slot.
 * @param startTime The start time of the time slot in the format "HH:mm".
 * @param endTime The end time of the time slot in the format "HH:mm".
 */
@Serializable
data class Slot(
    val id: Int,
    val startTime: String,
    val endTime: String,
)