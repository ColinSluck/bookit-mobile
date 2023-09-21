package com.diiage.bookit.ui.screens.createBookable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diiage.bookit.ui.core.composables.createBookable.BokableTypeList
import com.diiage.bookit.ui.core.composables.createBookable.Header
import com.diiage.bookit.ui.core.composables.createBookable.Home
import com.diiage.bookit.ui.core.composables.createBookable.Stepper

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateBookableScreen() {
    val viewModel: CreateBookableViewModel = viewModel()

    Column() {
        Header()
        Box() {
            when (viewModel.currentStep) {
                0 -> Step1Content()
                1 -> Step2Content()
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Stepper(
            currentStep = viewModel.currentStep,
            onNextStep = viewModel::onNextStep,
            onPreviousStep = viewModel::onPreviousStep
        )
    }
}

@Composable
fun Step1Content() {
    Home()
}

@Composable
fun Step2Content() {
    BokableTypeList()
}

@Preview(showBackground = true)
@Composable
fun CreateBookableScreenPreview() {
    CreateBookableScreen()
}