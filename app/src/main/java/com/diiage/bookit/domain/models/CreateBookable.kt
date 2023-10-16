package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateBookable (
        var name: String,
        var description: String,
        var place: String,
        var maxCapacity: Int,
        var bookableTypeId: Int,
)