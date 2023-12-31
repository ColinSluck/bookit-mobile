package com.diiage.bookit.ui.core.composables.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.Destination

@Composable
fun Navbar(
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .height(87.dp)
            .fillMaxWidth()
            .background(Color.White),
    ) {
        NavbarButton(
            icon = R.drawable.navbar_button_home,
            label = "Accueil",
            selected = navController.currentDestination?.route == Destination.Home.route,
            onClick = {
                navController.navigate(Destination.Home.route) {
                    launchSingleTop = true
                }
            }
        )

        NavbarButton(
            icon = R.drawable.navbar_button_booking,
            label = "Réservations",
            selected = navController.currentDestination?.route == Destination.Bookings.route,
            onClick = {
                navController.navigate(Destination.Bookings.route) {
                    launchSingleTop = true
                }
            }
        )

        NavbarButton(
            icon = R.drawable.navbar_button_profile,
            label = "Profil",
            selected = navController.currentDestination?.route == Destination.Profile.route,
            onClick = {
                navController.navigate(Destination.Profile.route) {
                    launchSingleTop = true
                }
            }
        )
    }
}