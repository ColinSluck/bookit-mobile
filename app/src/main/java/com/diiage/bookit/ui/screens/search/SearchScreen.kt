package com.diiage.bookit.ui.screens.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.composables.BookableCard
import com.diiage.bookit.ui.core.composables.search.SearchBar

@Composable
fun SearchScreen(navController: NavController,
                 search: String
) {
    Log.d("SearchScreen", "SearchScreen")

    val viewModel: SearchViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(search) {
        viewModel.init(search = search)
    }

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
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()

    ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    SearchBar(
                        firstName = state.firstName,
                        onClick = {
                            navController.navigate(Destination.Filter.route) {
                                launchSingleTop = true
                            }
                        },
                    )
                    Text(
                        text = "${state.totalBookable} résultat" + if (state.totalBookable > 1) "s" else "" + " :",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7A7A7A),
                            ),
                        modifier = Modifier
                            .padding(start = 39.dp, top = 8.dp)
                    )
                }
                Column (
                    modifier = Modifier
                    .verticalScroll(rememberScrollState())
                ){
                    state.bookables.forEach { bookable ->
                        run {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row (
                                    modifier = Modifier.padding(top = 16.dp)
                                ){
                                    BookableCard(bookable = bookable)
                                }
                            }
                        }
                    }

                    Column (
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (state.bookables.size < state.totalBookable) {
                            Button(onClick = { handleAction(SearchAction.LoadMoreBookables) }) {
                                Text(
                                    text = "Charger plus d'annonce",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFFFFFFFF)
                                    )
                                )
                            }
                        }
                    }
                    if (state.isLoading) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Chargement...",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF000000)
                                ),
                                modifier = Modifier
                                    .padding(top = 50.dp)
                            )
                        }
                    }
                }
        }
    }
}
