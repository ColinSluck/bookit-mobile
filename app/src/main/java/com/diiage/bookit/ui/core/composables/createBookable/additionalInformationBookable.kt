package com.diiage.bookit.ui.core.composables.createBookable

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.composables.InputForm
import com.diiage.bookit.ui.screens.createBookable.CreateBookableAction
import com.diiage.bookit.ui.screens.createBookable.CreateBookableState


@Composable
fun AddInformation(state: CreateBookableState, handleAction: (CreateBookableAction) -> Unit) {
    val stringName = remember { mutableStateOf(TextFieldValue(state.bookable.name)) }
    val stringLoc = remember { mutableStateOf(TextFieldValue(state.bookable.place)) }
    val stringDesc = remember { mutableStateOf(TextFieldValue(state.bookable.description)) }

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
                onChange = { handleAction(CreateBookableAction.OnNameChanged(it.text)) },
                characterLimit = 50
            )

            InputForm(
                label = "Lieu",
                textState = stringLoc,
                onChange = { handleAction(CreateBookableAction.OnLocationChanged(it.text)) },
                characterLimit = 200
            )

            InputForm(
                label = "Description",
                textState = stringDesc,
                onChange = { handleAction(CreateBookableAction.OnDescriptionChanged(it.text)) },
                characterLimit = 200
            )
        }
}
