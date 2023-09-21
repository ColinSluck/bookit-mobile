package com.diiage.bookit.ui.core.composables.createBookable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun BokableTypeList(
    bookableTypes: List<String> = listOf(
        "Maison",
        "Appartement",
        "Chambre",
        "Bateau",
        "Camping-car",
        "Tente",
        "Yourte",
        "Chalet",
        "Camping",
    )
) {
    Column {
        Text(
            text = "Parmis les éléments suivant, lequel décrit le mieux votre bien ?",
            Modifier.padding(horizontal = 16.dp),
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 28.5.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )
        
        bookableTypes.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                row.forEach { bookableType ->
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(
                                width = 2.dp,
                                color = Color(0xFF457B9D),
                                shape = RoundedCornerShape(size = 5.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                    ) {
                        Text(
                            text = bookableType,
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
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF457B9D)),
                    modifier = Modifier
                        .padding(5.dp)
                        .width(30.dp)
                        .height(30.dp)
                )

                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Créer une nouvelle catégorie",
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

@Preview
@Composable
fun BookableTypeListPreview() {
    BokableTypeList()
}