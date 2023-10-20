package com.diiage.bookit.data.remote.response

import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
internal data class BookableType (
    val Id : Int,
    val CreatedAt : String,
    val UpdatedAt : String,
    val Libelle : String,
)

