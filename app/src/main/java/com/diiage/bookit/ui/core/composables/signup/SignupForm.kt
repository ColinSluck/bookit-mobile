package com.diiage.bookit.ui.core.composables.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.ui.core.composables.InputForm

@Composable
fun SignUpForm() {
    val lastNameState = remember { mutableStateOf(TextFieldValue("")) }
    val firstNameState = remember { mutableStateOf(TextFieldValue("")) }
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }
    val confirmPasswordState = remember { mutableStateOf(TextFieldValue("")) }

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            InputForm(label = "Nom", textState = lastNameState, characterLimit = 50)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            InputForm(label = "Prénom", textState = firstNameState, characterLimit = 50)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            InputForm(label = "Adresse mail", textState = emailState, characterLimit = 200)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            InputForm(label = "Mot de passe", textState = passwordState, isPasswordField = true)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            InputForm(label = "Confirmer votre mot de passe", textState = confirmPasswordState, isPasswordField = true)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            SignUpButton(onClick = { /* TODO */ })
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Vous avez déjà un compte ? Cliquez ici",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF7A7A7A),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Composable
fun SignUpButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(69.dp)
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 5.dp),
            )
            .border(
                width = 1.dp,
                color = Color(0xFF457B9D),
                shape = RoundedCornerShape(size = 5.dp),
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Inscription",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF457B9D),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Preview
@Composable
fun SignUpFormPreview() {
    SignUpForm()
}