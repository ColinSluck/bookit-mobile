package com.diiage.bookit.domain.models

data class Bookable (
    val id: Int,
    val createAt: String,
    val updateAt: String,

    val name: String,
    val description: String,
    val place: String,
    val maxCapacity: Int,
    val bookableTypeId: Int,
)