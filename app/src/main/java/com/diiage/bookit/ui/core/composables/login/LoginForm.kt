package com.diiage.bookit.ui.core.composables.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
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
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.ui.core.composables.InputForm
import com.diiage.bookit.ui.screens.login.LoginAction
import com.diiage.bookit.ui.screens.login.LoginState

@Composable
fun LoginForm(
    handleAction: (LoginAction) -> Unit,
    state: LoginState
) {
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            InputForm(label = "Adresse mail", textState = emailState, characterLimit = 200)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp)
        ) {
            InputForm(label = "Mot de passe", textState = passwordState, isPasswordField = true)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            LoginButton(onClick = {
                val credentials = Credentials(emailState.value.text, passwordState.value.text)
                handleAction(LoginAction.OnConnect(credentials))
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
            val text = "Vous n'avez pas de compte ? "
            val clickableText = "Inscrivez-vous"

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
                            handleAction(LoginAction.OnSignup)
                        }
                }
            )
        }

    }
}

@Composable
fun LoginButton(onClick: () -> Unit, isLoading: Boolean = false) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(69.dp)
            .background(
                color = if (isLoading) Color.Gray else Color(0xFF457B9D),
                shape = RoundedCornerShape(size = 5.dp)
            )
            .clickable(enabled = !isLoading, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text(
                text = "Connexion",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}
