package com.diiage.bookit.ui.core.composables.createBookable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.bookit.R
import com.diiage.bookit.ui.screens.createBookable.CreateBookableAction

@Composable fun Header(
    handleAction: (CreateBookableAction) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ){
            Image(
                painter = painterResource(id = R.drawable.create_bookable_close),
                contentDescription = "Click for close",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(16.dp)
                    .width(30.dp)
                    .height(30.dp)
                    .clickable { handleAction(CreateBookableAction.OnCloseClicked) }
            )
        }
}
