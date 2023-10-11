package com.diiage.bookit.ui.core.composables.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import com.diiage.bookit.ui.screens.login.LoginAction
import com.diiage.bookit.ui.screens.login.LoginState

private typealias UIState = LoginState

@Composable
fun Login(
    state: UIState = UIState(),
    handleAction: (LoginAction) -> Unit
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
    ) {
        Column {
            LoginInformation()
            LoginForm(handleAction = handleAction)
        }
    }
}

@Composable
fun LoginInformation() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Login informations",
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 9.dp)
        ) {
            Text(
                text = "Bienvenue",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Left
                ),
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(bottom = 35.dp)
            ) {
        Text(
            text = "Connectez-vous pour continuer",
            style = TextStyle (
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF7A7A7A),
                textAlign = TextAlign.Left
                )
            )
        }
    }
}