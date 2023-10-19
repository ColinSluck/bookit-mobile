package com.diiage.bookit.ui.screens.createBookable


import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.CreateBookable
import com.diiage.bookit.domain.repositories.AuthRepository
import com.diiage.bookit.domain.repositories.BookableRepository
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class CreateBookableViewModel(application: Application) : ViewModel<CreateBookableState>(CreateBookableState(), application) {
    private val bookableRepository: BookableRepository by inject()


    var currentStep by mutableStateOf(0)

    val stepColors = listOf(
        Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9)
    )

    fun onNextStep() {
            if (currentStep < stepColors.size - 1) {
                currentStep++
            }
    }

    fun onPreviousStep() {
        if (currentStep > 0) {
            currentStep--
        } else {
            sendEvent(NavigationEvent.NavigateToProfile)
        }
    }

    fun onFinish() {
        currentStep = 5

        viewModelScope.launch {
            bookableRepository.createBookable(state.value.bookable)
        }
    }

    fun handleAction(action: CreateBookableAction) {
        when (action) {
            is CreateBookableAction.OnCloseClicked -> sendEvent(NavigationEvent.NavigateToProfile)
            is CreateBookableAction.OnNameChanged -> updateState {
                var bookable = state.value.bookable
                bookable.name = action.name
                copy(bookable = bookable)
            }
            is CreateBookableAction.OnLocationChanged -> updateState {
                var bookable = state.value.bookable
                bookable.place = action.location
                copy(bookable = bookable)
            }
            is CreateBookableAction.OnDescriptionChanged -> updateState {
                var bookable = state.value.bookable
                bookable.description = action.description
                copy(bookable = bookable)
            }
            is CreateBookableAction.OnCapacityChanged -> updateState {
                var bookable = state.value.bookable
                bookable.maxCapacity = action.maxCapacity.toInt()
                copy(bookable = bookable)
            }
        }
    }
}

data class CreateBookableState(
    val bookable : CreateBookable = CreateBookable("", "", "", 0, 2)
)

sealed interface CreateBookableAction {
    object OnCloseClicked : CreateBookableAction
    data class OnNameChanged(val name: String) : CreateBookableAction
    data class OnLocationChanged(val location: String) : CreateBookableAction
    data class OnDescriptionChanged(val description: String) : CreateBookableAction
    data class OnCapacityChanged(val maxCapacity: String) : CreateBookableAction
}