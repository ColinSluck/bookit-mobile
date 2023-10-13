package com.diiage.bookit.ui.screens.bookings

import android.app.Application
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.flow.Flow

class BookingsViewModel(application: Application) : ViewModel<BookingsState>(BookingsState(), application)

data class BookingsState(
    val nextBookings: List<String> = listOf("booking1", "booking2", "booking3"),
    val oldBookings: List<String> = listOf("booking4", "booking5", "booking6", "booking7")
)