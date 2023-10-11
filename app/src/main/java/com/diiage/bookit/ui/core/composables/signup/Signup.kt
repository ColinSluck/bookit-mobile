package com.diiage.bookit.ui.core.composables.signup

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
import com.diiage.bookit.ui.screens.signup.SignupAction
import com.diiage.bookit.ui.screens.signup.SignupState

private typealias UIState = SignupState

@Composable
fun SignUp(state: UIState = UIState(),
           handleAction: (SignupAction) -> Unit) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
    ) {
        Column {
            SignUpInformation()
            SignUpForm(handleAction = handleAction)
        }
    }
}

@Composable
fun SignUpInformation() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.signup_ill),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 9.dp)
        ) {
            Text(
                text = "Bienvenue !",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(bottom = 35.dp)
        ) {
            Text(
                text = "Inscrivez-vous pour profiter de notre plateforme !",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF7A7A7A),
                )
            )
        }
    }
}