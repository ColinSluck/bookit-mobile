package com.diiage.bookit.ui.core.composables.navbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R

@Composable
fun NavbarButton(
    icon: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val iconTint by animateColorAsState(if (selected) Color.Black else Color(0xFF7A7A7A), label = "")
    val textColor by animateColorAsState(if (selected) Color.Black else Color(0xFF7A7A7A), label = "")

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
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = label,
            tint = iconTint,
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        )

        Text(
            text = label,
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