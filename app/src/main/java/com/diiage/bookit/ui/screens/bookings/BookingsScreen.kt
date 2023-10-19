package com.diiage.bookit.ui.screens.bookings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.composables.BookableCard
import com.diiage.bookit.ui.core.composables.bookings.NoNextBookings
import com.diiage.bookit.ui.core.navigate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

private typealias UIState = BookingsState

@Composable
fun BookingsScreen(navController: NavController) {
    val viewModel: BookingsViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.events
            .onEach { event ->
                if (event is Destination.Bookable) {
                    navController.navigate(event)
                }
                else if (event is Destination.Filter) {
                    navController.navigate(event)
                }
            }.collect()
    }

    BookingsContent(
        state = state,
        handleAction = viewModel::handleAction
    )
}

@Composable
fun BookingsContent(
    state: UIState = UIState(),
    handleAction: (BookingsAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "VOS RÉSERVATIONS",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(700),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(state.isLoading) {
                CircularProgressIndicator(color = Color.White)
            } else if(state.bookings.isNotEmpty()) {
                state.bookings.forEach { booking ->
                    Row(
                        Modifier
                            .padding(bottom = 42.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ){
                            BookableCard(bookable = booking.bookable, booking = booking, onClick = { handleAction(
                                BookingsAction.OnSelectBookable(booking.bookableId)) })
                        }
                    }
                }

                Column (
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state.bookings.size < state.totalBooking) {
                        Button(onClick = { handleAction(BookingsAction.OnLoadMore) }) {
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
            } else {
                NoNextBookings(onClick = {handleAction(BookingsAction.OnBookClick)})
            }
        }
    }
}
