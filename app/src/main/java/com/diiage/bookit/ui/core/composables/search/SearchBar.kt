package com.diiage.bookit.ui.core.composables.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R

@Composable
fun SearchBar(
    firstName: String,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = "Bienvenue " + firstName + " !",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000)
            ),
            modifier = Modifier
                .padding(start = 39.dp, top = 50.dp)
        )
        Button(
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
            shape = RoundedCornerShape(29.dp),
            border = BorderStroke(1.dp, Color(0x80000000)),
            modifier = Modifier
                .padding(start = 23.dp, end = 23.dp)
                .width(384.dp)
                .height(68.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image14),
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
            ){
                Row(
                    modifier = Modifier
                        .padding(start = 36.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Text(
                        text = "Réservez maintenant !",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000)
                        )
                    )
                }
                Row(
                    Modifier
                        .padding(start = 34.dp)
                ){
                    Text(
                        text = "Une salle de réunion, une voiture...",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7A7A7A)
                        )
                    )
                }
            }
        }
    }
}
