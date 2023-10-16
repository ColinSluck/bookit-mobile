package com.diiage.bookit.ui.screens.bookings

import android.app.Application
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.repositories.BookableRepository
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import com.diiage.bookit.ui.screens.profile.ProfileAction
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.inject

class BookingsViewModel(application: Application) : ViewModel<BookingsState>(BookingsState(), application) {

    private val bookableRepository: BookableRepository by inject()

    init {
        fetchData(
            source = { bookableRepository.getAvailableBookables(state.value.page) }
        ) {

            onSuccess {
                updateState { copy(bookings = it.data, error = null) }
            }

            onFailure {
                updateState { copy(bookings = emptyList(), error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }

    fun handleAction(action: BookingsAction) {
        when (action) {
            is BookingsAction.OnSelectBooking -> selectBookable(action.id)
        }
    }

    private fun selectBookable(id: Int){
        sendEvent(NavigationEvent.NavigateToBookable)
    }
}

data class BookingsState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val page: Int = 1,
    val bookings: List<Bookable> = emptyList(),
)

sealed interface BookingsAction {
    data class OnSelectBooking(val id: Int) : BookingsAction
}