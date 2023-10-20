package com.diiage.bookit.ui.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Line(leftValue: Int, topValue: Int, rightValue: Int, bottomValue: Int, widthValue: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(leftValue.dp, topValue.dp, rightValue.dp, bottomValue.dp),
        contentAlignment = Alignment.Center
    ) {
        Divider(
            modifier = Modifier
                .padding(0.dp)
                .width(widthValue.dp)
                .background(color = Color(0x807A7A7A)),
            color = Color(0x807A7A7A),
            thickness = 1.dp
        )
    }
}