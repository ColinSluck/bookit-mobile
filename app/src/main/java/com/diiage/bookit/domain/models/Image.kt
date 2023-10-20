package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Image (
    val uploadedAt: String,
    val fileName: String,
    val contentType: String,
    val url: String,
    val bookableId: Int,
    val id: Int
){}