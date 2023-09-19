package com.diiage.bookit.ui.core.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R


@Composable
fun ConnexionView() {
    Column (
        modifier = Modifier
            .background(Color.White)
    ){

        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = "connexion image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 38.dp)
        )

        Text(
            text = "C'est dans la poche !",
            style = TextStyle
                (

            ),
            modifier = Modifier
            .padding(start = 50.dp)
        )

        Text(
            text = "Votre réservation est confirmée ! Vous pouvez la retrouver dans l’onglet “Mes réservations” pour inviter vos collaborateurs.",
            style = TextStyle
                (
                color = Color(0xFF7A7A7A),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(start = 0.dp)
        )



    }

}

@Preview
@Composable
fun Preview3() {
    ConnexionView()
}
