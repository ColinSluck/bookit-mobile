package com.diiage.bookit.data.remote.responses

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class BookableResponse(
    val id: Int,
    val createAt: String,
    val updateAt: String,

    val name: String,
    val description: String,
    val place: String,
    val maxCapacity: Int,
    val bookableTypeId: Int,
)