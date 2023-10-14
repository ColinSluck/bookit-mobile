package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Slot(
    val id: Int,
    val startTime: String,
    val endTime: String,
)