package com.diiage.bookit.ui.screens.bookable

import android.app.Application
import androidx.compose.ui.graphics.ImageBitmap
import com.diiage.bookit.ui.core.ViewModel

class BookableViewModel(application: Application) : ViewModel<BookableState>(BookableState(), application) {

}

data class BookableState(
    val initialImages: List<ImageBitmap> = listOf(),
    val title: String = "Title",
    val description: String = "Description",
    val people: Int = 0,
    val location: String = "Location",
    val materials: List<String> = listOf(),
    val available: Boolean = false
)