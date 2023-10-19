package com.diiage.bookit.ui.core.composables.createBookable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import com.diiage.bookit.ui.screens.createBookable.CreateBookableAction
import com.diiage.bookit.ui.screens.createBookable.CreateBookableState

@Composable
fun Confirmation(state: CreateBookableState, handleAction: (CreateBookableAction) -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.confirmation),
                contentDescription = "Confirmation",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            Text(
                text = "Félicitations !",
                Modifier.padding(horizontal = 16.dp),
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 28.5.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Votre annonce de location de salle a été ajoutée avec succès. Elle est maintenant visible pour les utilisateurs en quête d'un espace pour leurs événements.",
                Modifier.padding(horizontal = 16.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF7A7A7A),
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Column() {
                Button(
                    onClick = { handleAction(CreateBookableAction.onCreatedBookable) },
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(
                            color = Color(0xFF457B9D),
                            shape = RoundedCornerShape(size = 5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                ) {
                    Text(
                        text = "Fermer",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
        }
    }
}