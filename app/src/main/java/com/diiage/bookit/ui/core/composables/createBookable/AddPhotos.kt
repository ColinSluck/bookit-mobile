package com.diiage.bookit.ui.core.composables.createBookable

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.diiage.bookit.R
import com.diiage.bookit.ui.screens.createBookable.CreateBookableAction
import com.diiage.bookit.ui.screens.createBookable.CreateBookableState

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AddPhotos(
    state: CreateBookableState,
    handleAction: (CreateBookableAction) -> Unit,
) {
    val pickImagesLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        handleAction(CreateBookableAction.OnImagesSelected(uris))
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
                onClick = { pickImagesLauncher.launch("image/*") },
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

        state.selectedImages.forEach { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .padding(8.dp)
            )
        }
    }
}