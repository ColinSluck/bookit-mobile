package com.diiage.bookit.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DisplayText(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .padding(horizontal = 58.dp)
            .fillMaxWidth()
            .height(13.dp),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF7A7A7A),
        ),
    )
}

@Composable
fun ValueText(value: String) {
    Row {
        Text(
            text = value,
            modifier = Modifier
                .padding(horizontal = 58.dp)
                .fillMaxWidth()
                .height(15.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF000000)
            )
        )
    }
}

@Composable
fun PersonalInformation(lastnameValue: String, firstnameValue: String, emailValue: String) {
    Column(
        Modifier.background(color = Color(0xFFFFFFFF))
    ) {
        Text(
            text = "Information personnelles",
            modifier = Modifier
                .padding(horizontal = 46.dp)
                .width(197.dp)
                .height(21.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000)
            )
        )
        Row {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                DisplayText(value = "Nom")
                ValueText(value = lastnameValue)
                Line(
                    leftValue = 0,
                    topValue = 13,
                    rightValue = 0,
                    bottomValue = 13,
                    widthValue = 338
                )

                DisplayText(value = "Prénom")
                ValueText(value = firstnameValue)
                Line(
                    leftValue = 0,
                    topValue = 13,
                    rightValue = 0,
                    bottomValue = 13,
                    widthValue = 338
                )

                DisplayText(value = "Adresse Mail")
                ValueText(value = emailValue)
                Line(
                    leftValue = 0,
                    topValue = 13,
                    rightValue = 0,
                    bottomValue = 13,
                    widthValue = 338
                )
            }
        }
    }
}

@Preview
@Composable
fun PersonalInformationPreview() {
    PersonalInformation(lastnameValue = "MARTIN", firstnameValue = "Lucas", emailValue = "lucas.martin@diiage.org")
}