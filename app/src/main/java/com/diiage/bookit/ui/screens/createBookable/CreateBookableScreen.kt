package com.diiage.bookit.ui.screens.createBookable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diiage.bookit.ui.core.composables.createBookable.BokableTypeList
import com.diiage.bookit.ui.core.composables.createBookable.Header
import com.diiage.bookit.ui.core.composables.createBookable.Home
import com.diiage.bookit.ui.core.composables.createBookable.Stepper
import com.diiage.bookit.ui.core.composables.createBookable.AddInformation
import com.diiage.bookit.ui.core.composables.createBookable.MaxCapacity
import com.diiage.bookit.ui.core.composables.createBookable.Equipement
import com.diiage.bookit.ui.core.composables.createBookable.AddPhotos

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
                2 -> Step3Content()
                3 -> Step4Content()
                4 -> Step5Content()
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

@Composable
fun Step3Content() {
    Column {
        AddInformation()

        Spacer(modifier = Modifier.padding(16.dp))

        MaxCapacity()
    }
}

@Composable
fun Step4Content() {
    Equipement()
}

@Composable
fun Step5Content() {
    AddPhotos()
}

@Preview(showBackground = true)
@Composable
fun CreateBookableScreenPreview() {
    CreateBookableScreen()
}