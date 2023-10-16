package com.diiage.bookit.ui.screens.createBookable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.Screen
import com.diiage.bookit.ui.core.composables.createBookable.BokableTypeList
import com.diiage.bookit.ui.core.composables.createBookable.Header
import com.diiage.bookit.ui.core.composables.createBookable.Home
import com.diiage.bookit.ui.core.composables.createBookable.Stepper
import com.diiage.bookit.ui.core.composables.createBookable.AddInformation
import com.diiage.bookit.ui.core.composables.createBookable.MaxCapacity
import com.diiage.bookit.ui.core.composables.createBookable.Equipement
import com.diiage.bookit.ui.core.composables.createBookable.AddPhotos
import com.diiage.bookit.ui.core.composables.createBookable.Confirmation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateBookableScreen(navController: NavController) {
    val viewModel: CreateBookableViewModel = viewModel()

    LaunchedEffect(viewModel) {
        viewModel.events
            .onEach { event ->
                if (event is NavigationEvent.NavigateToProfile)
                    navController.navigate(Screen.Profile.route)
            }.collect()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header(handleAction = viewModel::handleAction)

        Column(
            modifier = Modifier.weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            when (viewModel.currentStep) {
                0 -> Step1Content()
                1 -> Step2Content()
                2 -> Step3Content()
                3 -> Step4Content()
                4 -> Step5Content()
                5 -> Step6Content()
            }
        }

        if (viewModel.currentStep != 5) {
            Stepper(
                currentStep = viewModel.currentStep,
                onNextStep = viewModel::onNextStep,
                onPreviousStep = viewModel::onPreviousStep,
                onFinish = viewModel::onFinish
            )
        }
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

@Composable
fun Step6Content() {
    Confirmation()
}

