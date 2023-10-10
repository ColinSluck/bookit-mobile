package com.diiage.bookit.ui.core.composables.createBookable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@Composable fun Home() {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.create_bookable_home),
                contentDescription = "Home",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            Text(
                text = "Parlez-nous de votre bien",
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
                text = "L'ajout d'un bien à l'application nécessite la saisie d'informations essentielles telles que les équipements disponibles et la capacité d'accueil, garantissant ainsi une expérience de réservation complète et personnalisée pour nos utilisateurs, tout en permettant aux propriétaires de présenter leur bien de manière détaillée.",
                Modifier.padding(horizontal = 16.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF7A7A7A),
                )
            )
        }
    }
}

@Preview @Composable fun HomePreview() {
    Home()
}