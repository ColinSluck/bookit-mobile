package com.diiage.bookit.ui.screens.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.ErrorMessage
import com.diiage.bookit.domain.models.Booking
import com.diiage.bookit.domain.models.User
import com.diiage.bookit.domain.repositories.BookingRepository
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.core.component.inject

class HomeViewModel(application: Application) : ViewModel<HomeState>(HomeState(), application) {

   private val preferencesRepository: PreferenceRepository by inject()
   private val bookingRepository: BookingRepository by inject()

   init {
      val userString = preferencesRepository.get("user")

      if(userString != null) {
         val user: User = Json.decodeFromString(userString)

         updateState { copy(firstName = user.firstName) }
      }

     getUserBooking()
   }

    fun handleAction(action: HomeAction) {
        when(action) {
            is HomeAction.OnBookableClicked -> sendEvent(Destination.Bookable(action.bookableId.toString()))
            is HomeAction.OnBookingsClicked -> sendEvent(Destination.Bookings)
            is HomeAction.OnBookClicked -> sendEvent(Destination.Filter)
        }
    }

   private fun getUserBooking() {
      viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                val response = bookingRepository.getBookings(1, 3)
                updateState {copy(bookings = bookings + response.data, totalBooking = response.totalCount)}
            } catch (e: Exception) {
               Log.e("HomeViewModel", "GetUserBooking: ${e.message}")
                updateState { copy(error = ErrorMessage.ServerError.message) }
            }
            updateState { copy(isLoading = false) }
      }
   }
}

data class HomeState(
   val isLoading: Boolean = true,
   val error: String? = null,
   val firstName : String = "",
   val bookings: List<Booking> = emptyList(),
   val totalBooking: Int = 0
)

sealed interface HomeAction {
    data class OnBookableClicked(val bookableId: Int) : HomeAction
    object OnBookingsClicked : HomeAction
    object OnBookClicked : HomeAction
}