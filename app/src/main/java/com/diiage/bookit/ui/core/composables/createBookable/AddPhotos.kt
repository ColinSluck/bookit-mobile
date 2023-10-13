package com.diiage.bookit.ui.core.composables.createBookable

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.diiage.bookit.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AddPhotos() {
    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }

    Column {
        Text(
            text = "Ajoutez quelques photos !",
            Modifier.padding(horizontal = 16.dp),
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 31.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )

        Box(
            Modifier
                .padding(16.dp)
        ) {
            Button(
                onClick = { galleryLauncher.launch("image/*") },
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF7A7A7A),
                        shape = RoundedCornerShape(size = 5.dp),
                    )
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
            )
            {
                Column {
                    Text(
                        text = "Téléchargez des photos depuis votre appareil",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        )
                    )

                    Text(
                        text = "Choisissez des photos pour mettre en avant votre bien",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 15.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7A7A7A),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }

        Column {
            selectImages.forEach {
                Image(
                    painter = rememberImagePainter(it),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(8.dp)
                )
            }
        }
    }
}