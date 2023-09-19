package com.diiage.bookit.ui.core.composables

import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DeconnectionButton() {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Tu as bien été déconnecté!", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .width(371.dp)
            .height(45.dp),
        colors = ButtonDefaults.outlinedButtonColors(Color(0xFFE63946)),
        shape = RoundedCornerShape(size = 5.dp)
    ) {
        Text(
            text = "Déconnexion",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF)
            )
        )
    }
}

@Preview
@Composable
fun DeconnectionButtonPreview() {
    DeconnectionButton()
}