package com.diiage.bookit.ui.screens.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.Screen
import com.diiage.bookit.ui.core.composables.signup.SignUp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SignupScreen(navController: NavController) {
    val viewModel: SignupViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.events
            .onEach { event ->
                if (event is NavigationEvent.NavigateToHome)
                    navController.navigate(Screen.Home.route)
                else if (event is NavigationEvent.NavigateToLogin)
                    navController.navigate(Screen.Login.route)
            }.collect()
    }

    SignupContent(
        state = state,
        handleAction = viewModel::handleAction
    )
}

@Composable
fun SignupContent(
    state: SignupState = SignupState(),
    handleAction: (SignupAction) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ){
        SignUp(state, handleAction = handleAction)
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen(navController = NavController(LocalContext.current))
}