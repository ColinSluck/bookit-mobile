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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.domain.models.Signup
import com.diiage.bookit.ui.core.composables.InputForm
import com.diiage.bookit.ui.screens.signup.SignupAction
import com.diiage.bookit.ui.screens.signup.SignupState

@Composable
fun SignUpForm(handleAction: (SignupAction) -> Unit, state: SignupState) {
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
            SignUpButton(onClick = {
                if (passwordState.value.text == confirmPasswordState.value.text) {
                    val signUp = Signup(emailState.value.text, passwordState.value.text, firstNameState.value.text, lastNameState.value.text)
                    handleAction(SignupAction.OnSignup(signUp))
                }
            }, isLoading = state.isLoading)
        }
        state.error?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFF9999),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            val text = "Vous avez déjà un compte ? "
            val clickableText = "Cliquez ici"

            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF7A7A7A))) {
                    append(text)
                }
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF7A7A7A),
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    pushStringAnnotation(
                        tag = "clickable",
                        annotation = clickableText
                    )
                    append(clickableText)
                    pop()
                }
            }

            ClickableText(
                text = annotatedString,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF7A7A7A),
                    textAlign = TextAlign.Center,
                ),
                onClick = { offset ->
                    annotatedString.getStringAnnotations(tag = "clickable", start = offset, end = offset)
                        .firstOrNull()?.let {
                            handleAction(SignupAction.OnLoginClick)
                        }
                }
            )
        }

    }
}

@Composable
fun SignUpButton(onClick: () -> Unit, isLoading: Boolean = false) {
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
                color = if (isLoading) Color.Gray else Color(0xFF457B9D),
                shape = RoundedCornerShape(size = 5.dp),
            )
            .clickable(enabled = !isLoading, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color(0xFF457B9D))
        } else {
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
}