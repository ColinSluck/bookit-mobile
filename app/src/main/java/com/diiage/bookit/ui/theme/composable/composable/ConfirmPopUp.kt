package com.diiage.bookit.ui.theme.composable.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R


@Composable
fun CoonexionPopUp() {
    Row (
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

    ){
        Column (

            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = "connexion image",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter

            )



            Text(
                text = "C'est dans la poche !",
                style = TextStyle
                    (
                    textAlign = TextAlign.Center
                ),
               modifier = Modifier
                 .padding(bottom = 5.dp, top = 5.dp)
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
                    .padding(bottom = 30.dp)
            )

            CloseButton {

            }

        }

    }

}


@Composable
fun InvitationPopUp() {
    Row (
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column (

            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = R.drawable.img_2),
                contentDescription = "invitation image",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter

            )



            Text(
                text = "Invitation envoyée !",
                style = TextStyle
                    (
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 5.dp)
            )

            CloseButton {

            }

        }

    }

}

@Composable
fun AreYouSurePopUp() {
    Row (
        modifier = Modifier
            .background(Color.White)
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
        ){


        Column (
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Êtes-vous sûr ?",
                style = TextStyle(fontSize = 22.sp),
                modifier = Modifier
                    .padding(top=10.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )

            Row (modifier = Modifier.padding(end=240.dp)){
                Text(text = "Salle de réunion D17",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                )
            }


            ConfirmButton {

            }
            ReturnButton {

            }
        }
    }


}

@Composable
fun CloseButton(onClick: () -> Unit) {
    Button(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color(red = 69, green = 123, blue = 157)),
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(

            Color.White
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Fermer", color =  Color(red = 69, green = 123, blue = 157) )
            }
        }
    )
}

@Composable
fun ConfirmButton(onClick: () -> Unit) {
    Button(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            Color(red = 69, green = 123, blue = 157)
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Confirmer", color = Color.White )
            }
        }
    )
}

@Composable
fun ReturnButton(onClick: () -> Unit) {
    Button(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color(red = 69, green = 123, blue = 157)),
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(

            Color.White
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Retour", color =  Color(red = 69, green = 123, blue = 157) )
            }
        }
    )
}


@Preview
@Composable
fun Preview3() {
    CoonexionPopUp()
}

@Preview
@Composable
fun Preview4() {
    InvitationPopUp()
}

@Preview
@Composable
fun Preview5() {
    AreYouSurePopUp()
}

