package com.diiage.bookit.ui.screens.login

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
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.composables.login.Login
import com.diiage.bookit.ui.core.navigate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.events
            .onEach { event ->
                if (event is NavigationEvent.NavigateToHome)
                    navController.navigate(Destination.Home)
                else if (event is NavigationEvent.NavigateToSignup)
                    navController.navigate(Destination.Signup)
            }.collect()
    }

    LoginContent(
        state = state,
        handleAction = viewModel::handleAction
    )
}

@Composable
fun LoginContent(
    state: LoginState = LoginState(),
    handleAction: (LoginAction) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ){
        Login(state = state, handleAction = handleAction)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}