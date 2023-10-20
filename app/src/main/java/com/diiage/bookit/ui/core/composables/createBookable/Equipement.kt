package com.diiage.bookit.ui.core.composables.createBookable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.diiage.bookit.ui.screens.createBookable.CreateBookableAction
import com.diiage.bookit.ui.screens.createBookable.CreateBookableState

@Composable
fun Equipement(state: CreateBookableState, handleAction: (CreateBookableAction) -> Unit
) {
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

        state.materials.chunked(2).forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                row.forEachIndexed { colIndex, bookableType ->
                    Button(
                        onClick = {
                           handleAction(CreateBookableAction.OnUpdateChecked(colIndex, !state.materialCheckedIds.contains(bookableType.id)))
                        },
                        modifier = if (state.materialCheckedIds.contains(bookableType.id)) modifierActive else modifierInActive,
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                    ) {
                        Text(
                            text = bookableType.libelle,
                            style = if (state.materialCheckedIds.contains(bookableType.id)) textStyleActive else textStyleInActive
                        )
                    }
                }
            }
        }

        Column (
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.materials.size < state.totalMaterial) {
                Button(onClick = { handleAction(CreateBookableAction.onLoadMoreMaterial) }) {
                    Text(
                        text = "Charger plus d'équipement",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF)
                        )
                    )
                }
            }
        }
        if (state.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Chargement...",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier
                        .padding(top = 50.dp)
                )
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