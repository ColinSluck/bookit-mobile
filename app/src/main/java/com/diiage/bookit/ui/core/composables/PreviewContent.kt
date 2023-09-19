package com.diiage.bookit.ui.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.diiage.bookit.ui.core.theme.BookItTheme

@Composable
fun PreviewContent(content: @Composable BoxScope.() -> Unit) {
    BookItTheme {
        Box(modifier = Modifier.background(Color.White), content = content)
    }
}

@Composable
fun PreviewContent(
    spacingVertical: Dp,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(spacingVertical),
    content: @Composable ColumnScope.() -> Unit
) {
    BookItTheme {
        Column(
            modifier = Modifier.background(Color.White),
            verticalArrangement = verticalArrangement,
            content = content
        )
    }
}

@Composable
fun PreviewContent(
    spacingHorizontal: Dp,
    horizontalArrangement: Arrangement.Horizontal =  Arrangement.spacedBy(spacingHorizontal),
    content: @Composable RowScope.() -> Unit
) {
    BookItTheme {
        Row(
            modifier = Modifier.background(Color.White),
            horizontalArrangement = horizontalArrangement,
            content = content
        )
    }
}