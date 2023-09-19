package com.diiage.bookit.ui.core.composables.navbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R

@Composable
fun NavbarButton(
    title: String = "No title",
    image: ImageVector = Icons.Default.Close,
    active: MutableState<String>,
    onClick: () -> Unit = {}
) {
    val isActive = active.value == title
    val iconTint by animateColorAsState(if (isActive) Color.Black else Color(0xFF7A7A7A), label = "")
    val textColor by animateColorAsState(if (isActive) Color.Black else Color(0xFF7A7A7A), label = "")

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp, 5.dp)
            .clickable (
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )

    ) {
        Icon(
            imageVector = image,
            contentDescription = title,
            tint = iconTint,
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        )

        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(400),
            color = textColor,
        )
    }
}

data class NavbarButtonItem(
    val title: String,
    val image: ImageVector,
)

@Preview
@Composable
fun NavbarButtonPreview() {
    val activeButton = remember { mutableStateOf("Accueil") }
    NavbarButton(
        "Accueil",
        ImageVector.vectorResource(R.drawable.navbar_button_home),
        activeButton
    )
}