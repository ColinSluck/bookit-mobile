package com.diiage.bookit.ui.screens.bookings

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.Screen
import com.diiage.bookit.ui.core.composables.bookings.CompactBookable
import com.diiage.bookit.ui.core.composables.PreviewContent
import com.diiage.bookit.ui.core.composables.bookings.NoNextBookings
import java.time.LocalDate
import java.util.*

private typealias UIState = BookingsState

@Composable
fun BookingsScreen(navController: NavController) {
    val viewModel: BookingsViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    BookingsContent(
        state = state,
        onSelectBooking = {
            navController.navigate(Screen.Bookable.route)
        }
    )
}

@Composable
fun BookingsContent(
    state: UIState = UIState(),
    onSelectBooking: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(35.dp, 0.dp, 35.dp, 0.dp)
    ) {
        item{
            Text(
                text = "VOS RÉSERVATIONS",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color.Black,
                modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 15.dp)
            )
        }

        item{
            NoNextBookings()
        }

        item{
            Text(
                text = "VOS RÉSERVATIONS",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color.Black,
                modifier = Modifier.padding(0.dp, 25.dp, 0.dp, 12.dp)
            )
        }

        items(state.oldBookings) {
            CompactBookable(
                title = it,
                location = "unknown",
                materials = "unknown",
                date = Date(),
                onClick = onSelectBooking
            )
        }
    }
}

@Preview
@Composable
private fun BookingsPreview() = PreviewContent {
    BookingsContent(
        onSelectBooking = {}
    )
}