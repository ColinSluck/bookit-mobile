package com.diiage.bookit.ui.functions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

fun calculateAccountAge(creationDate: String): String {
    try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS") // Format de la date
        val currentDate = Date() // Date actuelle

        val date = dateFormat.parse(creationDate) // Convertir la date de création en objet Date

        // Calculer la différence en millisecondes entre la date actuelle et la date de création
        val timeDiff = currentDate.time - date.time

        // Convertir la différence en jours, mois ou années en fonction de la durée écoulée
        val daysDiff = TimeUnit.MILLISECONDS.toDays(timeDiff)
        val monthsDiff = TimeUnit.MILLISECONDS.toDays(timeDiff) / 30 // Approximation de 30 jours par mois
        val yearsDiff = TimeUnit.MILLISECONDS.toDays(timeDiff) / 365 // Approximation de 365 jours par an

        return when {
            daysDiff < 30 -> {
                if (daysDiff <= 1L) "$daysDiff jour" else "$daysDiff jours"
            }
            monthsDiff < 12 -> {
                if (monthsDiff == 1L) "$monthsDiff mois" else "$monthsDiff mois"
            }
            else -> {
                if (yearsDiff == 1L) "$yearsDiff an" else "$yearsDiff ans"
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return "Erreur"
    }
}