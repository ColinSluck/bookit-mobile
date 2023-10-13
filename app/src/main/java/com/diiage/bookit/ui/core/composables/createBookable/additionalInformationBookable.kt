package com.diiage.bookit.ui.core.composables.createBookable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.composables.InputForm


@Composable
fun AddInformation() {
    val stringName = remember { mutableStateOf(TextFieldValue("")) }
    val stringLoc = remember { mutableStateOf(TextFieldValue("")) }
    val stringDesc = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Parmis les éléments suivant, lequel décrit le mieux votre bien ?",
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 28.5.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )

            InputForm(
                label = "Nom de l'annonce",
                textState = stringName,
                characterLimit = 50
            )

            InputForm(
                label = "Lieu",
                textState = stringLoc,
                characterLimit = 200
            )

            InputForm(
                label = "Description",
                textState = stringDesc,
                characterLimit = 200
            )
        }
}
