package com.diiage.bookit.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.bookit.R

@Composable
fun Navbar(
    buttons: List<NavbarButtonItem>,
    indexActive: Int
) {
    val activeButton = remember { mutableStateOf(buttons[indexActive].title) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .height(87.dp)
            .fillMaxWidth()
            .background(Color.White),
    ) {
        buttons.forEach { it ->
            NavbarButton(
                title = it.title,
                image = it.image,
                active = activeButton,
                onClick = { activeButton.value = it.title }
            )
        }
    }
}

@Preview(name = "Navbar preview 1", showBackground = true)
@Composable
fun NavbarPreview1() {
    Navbar(
        buttons = listOf(
            NavbarButtonItem(
                title = "Accueil",
                image = ImageVector.vectorResource(R.drawable.navbar_button_home),
            ),
            NavbarButtonItem(
                title = "Réservations",
                image = ImageVector.vectorResource(R.drawable.navbar_button_booking),
            ),
            NavbarButtonItem(
                title = "Profil",
                image = ImageVector.vectorResource(R.drawable.navbar_button_profile),
            ),
        ),
        indexActive = 0
    )

}