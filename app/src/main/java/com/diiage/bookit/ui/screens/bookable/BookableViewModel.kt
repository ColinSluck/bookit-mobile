package com.diiage.bookit.ui.screens.bookable

import android.app.Application
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.ViewModel

class BookableViewModel(application: Application) : ViewModel<BookableState>(BookableState(), application) {

}

data class BookableState(
    val initialImages: List<Int> = listOf(
        R.drawable.bookable_placeholder,
        R.drawable.bookable_placeholder_2,
        R.drawable.bookable_placeholder_3,
        R.drawable.bookable_placeholder_4
        ),
    val title: String = "Salle de réunion D17",
    val description: String = "La salle de réunion est un espace dédié aux réunions et aux discussions professionnelles. Elle est équipée de tables, de chaises, d'un matériel audiovisuel et d'une machine à café pour faciliter les présentations et offrir un moment de détente aux participants. Cet espace permet aux collaborateurs de se réunir, d'échanger des idées, de prendre des décisions importantes et de profiter d'une pause café. La salle de réunion est un lieu essentiel pour favoriser la collaboration, la productivité et le bien-être au sein de l'entreprise.",
    val people: Int = 6,
    val location: String = "1er étage",
    val materials: List<String> = listOf("Tableau", "Machine à café"),
    val available: Boolean = false
)