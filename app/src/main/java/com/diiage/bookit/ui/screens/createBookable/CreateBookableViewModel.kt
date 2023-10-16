package com.diiage.bookit.ui.screens.createBookable


import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel

class CreateBookableViewModel(application: Application) : ViewModel<CreateBookableState>(CreateBookableState(), application) {
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
        }
    }

    fun onFinish() {
        currentStep = 5
    }

    fun handleAction(action: CreateBookableAction) {
        when (action) {
            is CreateBookableAction.OnCloseClicked -> sendEvent(NavigationEvent.NavigateToProfile)
        }
    }

}

data class CreateBookableState(
    val bookable : Bookable? = null
)

sealed interface CreateBookableAction {
    object OnCloseClicked : CreateBookableAction
}