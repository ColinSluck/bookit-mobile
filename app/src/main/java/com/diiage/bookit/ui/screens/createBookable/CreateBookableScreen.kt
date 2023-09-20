package com.diiage.bookit.ui.screens.createBookable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diiage.bookit.ui.core.composables.createBookable.Stepper
import com.diiage.bookit.ui.core.composables.createBookable.Home
import com.diiage.bookit.ui.core.composables.createBookable.Header

@Composable
fun CreateBookableScreen() {
    val viewModel: CreateBookableViewModel = viewModel()

    Column {
        when (viewModel.currentStep) {
            0 -> Step1Content()
        }

        Stepper(
            currentStep = viewModel.currentStep,
            onNextStep = viewModel::onNextStep,
            onPreviousStep = viewModel::onPreviousStep
        )
    }
}

@Composable
fun Step1Content() {
    Column {
        Header()
        Home()
    }
}

@Preview(showBackground = true)
@Composable
fun CreateBookableScreenPreview() {
    CreateBookableScreen()
}