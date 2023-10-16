package com.diiage.bookit.ui.screens.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController,
                 search: String
) {
    val viewModel: SearchViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    viewModel.init(search = search)

    SearchContent(
        state = state,
        navController = navController,
        handleAction = viewModel::handleAction
    )
}

@Composable
fun SearchContent(
    state: SearchState = SearchState(),
    navController: NavController,
    handleAction: (SearchAction) -> Unit
) {
    Text(text ="Search")
    Text(text = state.totalBookable.toString())
}