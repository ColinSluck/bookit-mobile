/**
 * Ce fichier contient la définition de la classe de modèle "Bookable" utilisée pour représenter
 * des objets réservables. Cette classe est sérialisable pour une utilisation avec la bibliothèque
 * de sérialisation Kotlin.
 *
 * La classe "Bookable" comprend les propriétés suivantes :
 * - [id]: Identifiant unique de l'objet "bookable".
 * - [createdAt]: Date de création de l'objet.
 * - [updatedAt]: Date de mise à jour de l'objet.
 * - [name]: Nom de l'objet "bookable".
 * - [description]: Description de l'objet.
 * - [place]: Lieu associé à l'objet.
 * - [maxCapacity]: Capacité maximale de l'objet.
 * - [bookableTypeId]: Identifiant du type de "bookable".
 * - [images]: Liste d'URLs des images associées (peut être nulle).
 * - [materials]: Liste de matériaux associés (peut être nulle).
 */
package com.diiage.bookit.domain.models


import kotlinx.serialization.Serializable


@Serializable
data class Bookable (
    val id: Int,                 // Identifiant unique de l'objet "bookable"
    val createdAt: String,       // Date de création de l'objet
    val updatedAt: String,       // Date de mise à jour de l'objet

    val name: String,            // Nom de l'objet "bookable"
    val description: String,     // Description de l'objet
    val place: String,           // Lieu associé à l'objet
    val maxCapacity: Int,        // Capacité maximale de l'objet
    val bookableTypeId: Int,     // Identifiant du type de "bookable"

    val images: List<String>? = null,       // Liste d'URLs des images associées (peut être nulle)
    val materials: List<Material>? = null, // Liste de matériaux associés (peut être nulle)
)