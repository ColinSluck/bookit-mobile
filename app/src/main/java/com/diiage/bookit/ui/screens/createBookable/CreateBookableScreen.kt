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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.composables.createBookable.BokableTypeList
import com.diiage.bookit.ui.core.composables.createBookable.Header
import com.diiage.bookit.ui.core.composables.createBookable.Home
import com.diiage.bookit.ui.core.composables.createBookable.Stepper
import com.diiage.bookit.ui.core.composables.createBookable.AddInformation
import com.diiage.bookit.ui.core.composables.createBookable.MaxCapacity
import com.diiage.bookit.ui.core.composables.createBookable.Equipement
import com.diiage.bookit.ui.core.composables.createBookable.AddPhotos
import com.diiage.bookit.ui.core.composables.createBookable.Confirmation
import com.diiage.bookit.ui.core.navigate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

private typealias UIState = CreateBookableState
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateBookableScreen(navController: NavController) {
    val viewModel: CreateBookableViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.events
            .onEach { event ->
                if (event is NavigationEvent.NavigateToProfile)
                    navController.navigate(Destination.Profile)
                else if (event is Destination.Bookable)
                    navController.navigate(event)
            }.collect()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header(handleAction = viewModel::handleAction)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            when (viewModel.currentStep) {
                0 -> Step1Content()
                1 -> Step2Content()
                2 -> Step3Content(state, handleAction = viewModel::handleAction)
                3 -> Step4Content(state, handleAction = viewModel::handleAction)
                4 -> Step5Content(state, handleAction = viewModel::handleAction)
                5 -> Step6Content(state, handleAction = viewModel::handleAction)
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
fun Step3Content(state: CreateBookableState, handleAction: (CreateBookableAction) -> Unit) {
    Column {
        AddInformation(state, handleAction)

        Spacer(modifier = Modifier.padding(16.dp))

        MaxCapacity(handleAction)
    }
}

@Composable
fun Step4Content(state: CreateBookableState, handleAction: (CreateBookableAction) -> Unit) {
    Equipement(state = state, handleAction = handleAction)
}

@Composable
fun Step5Content(state: CreateBookableState, handleAction: (CreateBookableAction) -> Unit) {
    AddPhotos(state, handleAction)
}

@Composable
fun Step6Content(state: CreateBookableState, handleAction: (CreateBookableAction) -> Unit) {
    Confirmation(state = state, handleAction = handleAction)
}

