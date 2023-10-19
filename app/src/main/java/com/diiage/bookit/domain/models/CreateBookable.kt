package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

/**
 * The CreateBookable data class represents a model for creating a new bookable item.
 *
 * @param name: The name of the bookable item.
 * @param description: The description or details of the bookable item.
 * @param place: The location or place where the bookable item is available.
 * @param maxCapacity: The maximum capacity or number of people the bookable item can accommodate.
 * @param bookableTypeId: The unique identifier for the bookable item's type.
 * @param materialIds: A list of unique identifiers for materials associated with the bookable item.
 */
@Serializable
data class CreateBookable (
        var name: String,
        var description: String,
        var place: String,
        var maxCapacity: Int,
        var bookableTypeId: Int,
        var materialIds: List<Int> = emptyList()
)