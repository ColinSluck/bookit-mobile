package com.diiage.bookit.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
internal class Slot (

    val Id : Int,
    val StartTime : String,
    val EndTime : String

)