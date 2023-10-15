package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Material(
    val id: Int,
    val libelle: String,
    val bookableTypeId: Int,
    val createdAt: String,
    val updatedAt: String,
) {}