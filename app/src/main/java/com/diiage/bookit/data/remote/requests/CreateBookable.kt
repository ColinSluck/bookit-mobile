package com.diiage.bookit.data.remote.requests

data class CreateBookable (
    val name: String,
    val description: String,
    val place: String,
    val maxCapacity: Int,
    val bookableTypeId: Int,
)