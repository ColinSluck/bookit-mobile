package com.diiage.bookit.ui.core.composables.createBookable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R

@Composable
fun MaxCapacity() {
    var count by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Quelle est la capacité d’accueil maximale de votre bien ?",
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 28.5.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )

        Line()

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (count <= 1) "Personne" else "Personnes",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            CircularButton(
                onClick = {
                    if (count > 0) {
                        count--
                    }
                },
                text = "-"
            )


            Text(
                text = "$count",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                ),
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )

            CircularButton(
                onClick = { count++ },
                text = "+"
            )

        }
    }
    Line()

}

@Composable
fun Line() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Divider(
            modifier = Modifier
                .background(color = Color(0x807A7A7A)),
            color = Color(0x807A7A7A),
            thickness = 1.dp
        )
    }
}

@Composable
fun CircularButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
        modifier = Modifier
            .size(48.dp)
            .border(
                width = 2.dp,
                color = Color(0xFF7A7A7A),
                shape = CircleShape
            ),
    ) {
        Text(
            text = text, style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF7A7A7A),
            )
        )
    }
}

@Preview
@Composable
fun CircularButtonPreview() {
    CircularButton(onClick = {}, text = "+")
}

@Preview
@Composable
fun MaxCapacityPreview() {
    MaxCapacity()
}