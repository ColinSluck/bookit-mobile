/**
 * Fichier : BookingsViewModel.kt
 *
 * Description :
 * Ce fichier contient la classe ViewModel chargée de gérer les réservations dans l'application. Le ViewModel
 * gère diverses actions liées aux réservations, communique avec BookingRepository pour récupérer et manipuler les données,
 * et fournit ces données aux composants d'interface utilisateur associés.
 *
 * Dépendances :
 * - AndroidX ViewModel
 * - Koin pour l'injection de dépendances
 *
 * Notes importantes :
 * - Ce ViewModel est conçu pour interagir avec la fonctionnalité des réservations de l'application.
 * - Il implémente une architecture basée sur l'état pour gérer l'état de l'interface utilisateur et les données.
 * - Le ViewModel communique avec BookingRepository pour récupérer et mettre à jour les informations de réservation.
 * - Il prend en charge des actions telles que la réservation, la sélection d'un élément réservable et le chargement de plus de réservations.
 * - L'état du ViewModel comprend des informations sur l'état de chargement, les erreurs, les réservations récupérées et la pagination.
 * - La gestion des exceptions est en place pour traiter les erreurs réseau et serveur.
 * - Les événements d'interface utilisateur sont envoyés à l'aide de la classe Destination à des fins de navigation.
 * - Le ViewModel est conçu pour être utilisé en conjonction avec les composants d'interface utilisateur associés.
 */
package com.diiage.bookit.ui.screens.bookings

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.ErrorMessage
import com.diiage.bookit.domain.models.Booking
import com.diiage.bookit.domain.repositories.BookingRepository
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BookingsViewModel(application: Application) : ViewModel<BookingsState>(BookingsState(), application) {

    private val bookingRepository: BookingRepository by inject()

    init {
        getUserBooking()
    }

    fun handleAction(action: BookingsAction) {
        when(action) {
            is BookingsAction.OnBookClick -> sendEvent(Destination.Filter)
            is BookingsAction.OnSelectBookable -> sendEvent(Destination.Bookable(action.id.toString()))
            is BookingsAction.OnLoadMore -> getUserBooking()
        }
    }

    private fun loadMore() {
        updateState { copy(page = page + 1) }
        getUserBooking()
    }

    private fun getUserBooking() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                val response = bookingRepository.getBookings(state.value.page, 10)
                updateState {copy(bookings = bookings + response.data, totalBooking = response.totalCount)}
            } catch (e: Exception) {
                Log.e("HomeViewModel", "GetUserBooking: ${e.message}")
                updateState { copy(error = ErrorMessage.ServerError.message) }
            }
            updateState { copy(isLoading = false) }
        }
    }
}

sealed interface BookingsAction {
    object OnBookClick: BookingsAction
    data class OnSelectBookable(val id: Int): BookingsAction
    object OnLoadMore: BookingsAction
}

data class BookingsState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val bookings: List<Booking> = emptyList(),
    val totalBooking: Int = 0,
    val page: Int = 1
)