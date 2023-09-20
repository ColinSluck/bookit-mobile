package com.diiage.bookit.ui.screens.createBookable


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class CreateBookableViewModel : ViewModel() {
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
}
