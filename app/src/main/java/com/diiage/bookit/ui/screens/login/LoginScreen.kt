package com.diiage.bookit.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.ui.core.composables.login.Login

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = viewModel()
    val state by viewModel.state.collectAsState()

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
    Login(state = state, handleAction = handleAction)
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}