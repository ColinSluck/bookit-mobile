package com.diiage.bookit.ui.core.composables.bookings

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import com.diiage.bookit.ui.screens.bookings.BookingsAction

@Composable
fun NoNextBookings(
    onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color(0xFF7A7A7A), shape = RoundedCornerShape(size = 5.dp))
            .width(345.dp)
            .height(345.dp)
            .padding(0.dp, 15.dp, 0.dp, 25.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
        ){
            Image(
                painterResource(id = R.drawable.no_next_bookings),
                contentDescription = "",
                modifier = Modifier
                    .width(75.dp)
                    .height(75.dp)
            )

            Text(
                text = "Aucune réservation ne vous attend...",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color.Black
            )

            Text(
                text = "Ne perdez pas de temps et réservez rapidement l’équipement dont vous avez besoin !",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF7A7A7A),
                textAlign = TextAlign.Center,
            )

            Button(
                onClick = { onClick() },
                shape = RoundedCornerShape(size = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF457B9D),
                    disabledContainerColor = Color(0xFF7A7A7A),
                    contentColor = Color.White,
                ),
                modifier = Modifier
                    .width(280.dp)
                    .height(45.dp)
            ) {
                Text(
                    text = "Réserver maintenant",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}