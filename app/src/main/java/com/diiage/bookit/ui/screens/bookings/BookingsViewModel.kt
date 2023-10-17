package com.diiage.bookit.ui.screens.bookings

import android.app.Application
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.ViewModel

class BookingsViewModel(application: Application) : ViewModel<BookingsState>(BookingsState(), application) {
    fun handleAction(action: BookingsAction) {
        when(action) {
            is BookingsAction.OnBookClick -> sendEvent(Destination.Filter)
            is BookingsAction.OnSelectBookable -> sendEvent(Destination.Bookable(action.id.toString()))
        }
    }
}

sealed interface BookingsAction {
    object OnBookClick: BookingsAction
    data class OnSelectBookable(val id: Int): BookingsAction
}

data class BookingsState(
    val nextBookings: List<String> = listOf("booking1", "booking2", "booking3"),
    val oldBookings: List<String> = listOf("booking4", "booking5", "booking6", "booking7")
)