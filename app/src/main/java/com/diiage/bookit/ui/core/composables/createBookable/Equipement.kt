package com.diiage.bookit.ui.core.composables.createBookable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R

@Composable
fun Equipement(
    equipement: List<String> = listOf(
        "Machine à café",
        "Climatisation",
        "Chauffage",
        "Wifi",
        "Télévision",
        "Tableau blanc",
        "Tableau",
        "Vidéoprojecteur",
        "Micro",
        "Enceinte",
    )
) {
    val activeStates = remember { mutableStateListOf(*Array(equipement.size) { 0 }) }

    val modifierInActive = Modifier
        .padding(8.dp)
        .border(
            width = 2.dp,
            color = Color(0xFF457B9D),
            shape = RoundedCornerShape(size = 5.dp)
        )
    val modifierActive = Modifier
        .padding(8.dp)
        .background(color = Color(0xFF457B9D), shape = RoundedCornerShape(size = 5.dp))

    val textStyleInActive = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontWeight = FontWeight(700),
        color = Color(0xFF457B9D),
    )
    val textStyleActive = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontWeight = FontWeight(700),
        color = Color.White,
    )

    Column {
        Text(
            text = "Quels sont les équipements dont dispose votre bien?",
            Modifier.padding(horizontal = 16.dp),
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 28.5.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )

        equipement.chunked(2).forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                row.forEachIndexed { colIndex, bookableType ->
                    Button(
                        onClick = {
                            activeStates[rowIndex * 2 + colIndex] = 1 - activeStates[rowIndex * 2 + colIndex]
                        },
                        modifier = if (activeStates[rowIndex * 2 + colIndex] == 1) modifierActive else modifierInActive,
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                    ) {
                        Text(
                            text = bookableType,
                            style = if (activeStates[rowIndex * 2 + colIndex] == 1) textStyleActive else textStyleInActive
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color(0xFF457B9D),
                        shape = RoundedCornerShape(size = 5.dp)
                    ),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image21),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    colorFilter = ColorFilter.tint(Color(0xFF457B9D)),
                    modifier = Modifier
                        .padding(5.dp)
                        .width(30.dp)
                        .height(30.dp)
                )

                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Créer un nouvel équipement",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF457B9D),
                    )
                )
            }
        }
    }
}