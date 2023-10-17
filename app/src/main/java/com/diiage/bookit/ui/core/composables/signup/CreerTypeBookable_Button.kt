package com.diiage.bookit.ui.core.composables.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreerTypeBookableButton(onCloseClick: () -> Unit) {
    var isComponentVisible by remember { mutableStateOf(false) }
    Button(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onCloseClick,
        colors = ButtonDefaults.buttonColors(
            Color(red = 69, green = 123, blue = 157)
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Creer un type de réservation", color = Color.White)
            }
        }
    )
}

@Preview
@Composable
fun Preview() {
    CreerTypeBookableButton {

    }
}