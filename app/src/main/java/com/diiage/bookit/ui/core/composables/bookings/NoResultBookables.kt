package com.diiage.bookit.ui.core.composables.bookings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
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

@Composable
fun NoResult() {
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
        ){
        Image(
            painterResource(id = R.drawable.img_9),
            contentDescription = "pas de résulats",
            modifier = Modifier
                .width(75.dp)
                .height(75.dp)
        )

        Text(text = "Oh non.. aucune salle ne correspond à vos critères.",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(400),
            color = Color.Black)


        Text(text = "Voici cependant une liste de salles qui ne sont pas disponibles sur vos plages horaires. Vous pouvez essayer de trouver un arrangement avec le loueur actuel.",
            fontSize = 10.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF7A7A7A),
            textAlign = TextAlign.Center,
            )

        Spacer(modifier = Modifier
            .height(30.dp))

        Divider()

        Spacer(modifier = Modifier
            .height(30.dp))

                // Affichage des bookable card
               /*
                bookableView(bookableName = "Salle de réunion D17", bookableLoc ="1er étage" , bookableOptions = "Tableau blanc, machine à café...", personNumber = "6", bookingDate = "14/09", bookingTime = "10:30")

                bookableView(bookableName = "Salle de réunion D17", bookableLoc ="1er étage" , bookableOptions = "Tableau blanc, machine à café...", personNumber = "6", bookingDate = "14/09", bookingTime = "10:30")
                */

    }


}

@Preview
@Composable
fun PreviewNoResult(){
    NoResult()
}