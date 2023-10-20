package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable
/**
 * This data class, Material, represents a material item used in the context of bookable types.
 *
 * @param id The unique identifier of the material.
 * @param libelle The label or name of the material.
 * @param bookableTypeId The ID of the bookable type associated with the material.
 * @param createdAt The timestamp indicating the creation date of the material.
 * @param updatedAt The timestamp indicating the last update date of the material.
 */
@Serializable
data class Material(
    val id: Int,
    val libelle: String,
    val bookableTypeId: Int,
    val createdAt: String,
    val updatedAt: String,
) {}