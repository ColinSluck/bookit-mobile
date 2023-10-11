package com.diiage.bookit.ui.screens.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diiage.bookit.R

private typealias UIState = FilterState

@Composable
fun FilterScreen() {
    val viewModel: FilterViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    FilterContent(
        state = state
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterContent(
    state: UIState = UIState(),
) {
    Box(
        modifier = Modifier
            .background(Color(0x80898989))
            .padding(top = 39.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "Close",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "Filtres",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun FilterScreenPreview() {
    FilterContent()
}