package com.diiage.bookit.ui.screens.bookable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.composables.PreviewContent

private typealias UIState = BookableState
@Composable
fun BookableScreen(navController: NavController) {
    val viewModel: BookableViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    BookableContent(
        state = state,
    )
}

@Composable
fun BookableContent(
    state: UIState = UIState(),
) {

    var images by remember { mutableStateOf(state.initialImages) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = rememberImagePainter(data = images?.get(0), builder = {crossfade(true)}),
                contentScale = ContentScale.FillBounds,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(356.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 50.dp, 0.dp))
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(0.dp, 10.dp, 0.dp, 5.dp))
            ) {
                //foreach image in images except first
                images?.drop(1)?.drop(0)?.forEachIndexed { index, image ->
                    Image(
                        painter = rememberImagePainter(data = images?.get(index), builder = {crossfade(true)}),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "",
                        modifier = Modifier
                            .width(85.dp)
                            .height(85.dp)
                            .clip(RoundedCornerShape(29.dp))
                            .clickable {
                                val updatedImages = images!!.toMutableList()
                                updatedImages[0] = image
                                updatedImages[index + 1] = images!![0]
                                images = updatedImages
                            }
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(15.dp, 5.dp, 15.dp, 0.dp))
            ) {
                Text(
                    text = state.title,
                    fontSize = 26.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(700),
                    color = Color.Black
                )

                Text(
                    text = if (state.available) "Disponible" else "Indisponible",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(400),
                    color = if (state.available) Color(0xFF457B9D) else Color(0xFFE63946),
                )
            }

            Text(
                text = state.description,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(400),
                color = Color(0xFF7A7A7A),
                modifier = Modifier
                    .padding(PaddingValues(15.dp, 10.dp))
            )
            
            Text(
                text = "Équipements disponibles :",
                fontSize = 18.sp,
                //fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color.Black,
            )

            Row {
                Text(text = "${state.people} personnes maximum")
            }

            Row {
                Text(text = state.location)
            }

            state.materials.forEach {
                Row {
                    Text(text = it)
                }
            }

            Spacer(Modifier.height(94.dp))
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(94.dp)
                .background(Color.White)
                .align(Alignment.BottomCenter),
        )
        {
            Button(
                onClick = { /*TODO*/ },
                enabled = state.available,
                shape = RoundedCornerShape(size = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF457B9D),
                    disabledContainerColor = Color(0xFF7A7A7A),
                    contentColor = Color.White,
                ),
                modifier = Modifier
                    .width(280.dp)
                    .height(45.dp)
            ) {
                Text(
                    text = "Réserver",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }



}

@Preview
@Composable
private fun BookablePreview() = PreviewContent {
    BookableContent()
}