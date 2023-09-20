package com.diiage.bookit.ui.screens.bookable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R

@Composable
fun BookableView(
    initialImages: List<ImageBitmap>,
    title: String = "Title",
    description: String = "Description",
    people: Int = 0,
    location: String = "Location",
    materials: List<String>,
    available: Boolean = false
) {

    var images by remember { mutableStateOf(initialImages) }

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
                bitmap = images[0],
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
                images.drop(1).drop(0).forEachIndexed { index, image ->
                    Image(
                        bitmap = image,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "",
                        modifier = Modifier
                            .width(85.dp)
                            .height(85.dp)
                            .clip(RoundedCornerShape(29.dp))
                            .clickable {
                                val updatedImages = images.toMutableList()
                                updatedImages[0] = image
                                updatedImages[index + 1] = images[0]
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
                    text = title,
                    fontSize = 26.sp,
                    //fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(700),
                    color = Color.Black
                )

                Text(
                    text = if (available) "Disponible" else "Indisponible",
                    fontSize = 12.sp,
                    //fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(400),
                    color = if (available) Color(0xFF457B9D) else Color(0xFFE63946),
                )
            }

            Text(
                text = description,
                fontSize = 18.sp,
                //fontFamily = FontFamily(Font(R.font.poppins_bold)),
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
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.bookable_placeholder),
                    contentDescription = ""
                )
                
                Text(text = "$people personnes maximum")
            }

            Row {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.bookable_placeholder),
                    contentDescription = ""
                )

                Text(text = location)
            }

            materials.forEach {
                Row {
                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.bookable_placeholder),
                        contentDescription = ""
                    )

                    Text(text = it)
                }
            }

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
                enabled = available,
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
                )
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun BookableViewPreview(){
    BookableView(
        title = "Salle de réunion D17",
        initialImages = listOf(
            ImageBitmap.imageResource(R.drawable.bookable_placeholder),
            ImageBitmap.imageResource(R.drawable.bookable_placeholder_2),
            ImageBitmap.imageResource(R.drawable.bookable_placeholder_3),
            ImageBitmap.imageResource(R.drawable.bookable_placeholder_4)
        ),
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ut felis ac erat imperdiet vehicula at quis lacus. Ut aliquam velit libero, id cursus enim scelerisque ac. Curabitur gravida tempor ex, pulvinar convallis massa mollis in. Nullam eu tempor orci. Duis lorem massa, dictum vel semper eget, tempus sagittis ante. Aliquam tincidunt at nulla ut sollicitudin. Donec commodo tellus eu rutrum aliquet. Praesent eget purus nec risus iaculis malesuada. Donec lobortis dui augue, sit amet feugiat enim viverra eget. Nulla arcu nibh, tristique vel semper quis, interdum sed tellus. Aliquam vel fermentum lectus. Etiam condimentum tincidunt porta. Donec nec scelerisque magna. Cras mauris nibh, porttitor a blandit in, egestas id urna. Vestibulum iaculis vel urna sagittis commodo. Nullam a mauris ut massa accumsan luctus.",
        location = "D17",
        materials = listOf("Matériel 1", "Matériel 2", "Matériel 3"),
        available = true
    )
}

@Preview(showBackground = true)
@Composable
fun BookableViewPreview2(){
    BookableView(
        title = "Salle de réunion D17",
        initialImages = listOf(
            ImageBitmap.imageResource(R.drawable.bookable_placeholder),
            ImageBitmap.imageResource(R.drawable.bookable_placeholder_2),
            ImageBitmap.imageResource(R.drawable.bookable_placeholder_3),
            ImageBitmap.imageResource(R.drawable.bookable_placeholder_4)
        ),
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ut felis ac erat imperdiet vehicula at quis lacus. Ut aliquam velit libero, id cursus enim scelerisque ac. Curabitur gravida tempor ex, pulvinar convallis massa mollis in. Nullam eu tempor orci. Duis lorem massa, dictum vel semper eget, tempus sagittis ante. Aliquam tincidunt at nulla ut sollicitudin. Donec commodo tellus eu rutrum aliquet. Praesent eget purus nec risus iaculis malesuada. Donec lobortis dui augue, sit amet feugiat enim viverra eget. Nulla arcu nibh, tristique vel semper quis, interdum sed tellus. Aliquam vel fermentum lectus. Etiam condimentum tincidunt porta. Donec nec scelerisque magna. Cras mauris nibh, porttitor a blandit in, egestas id urna. Vestibulum iaculis vel urna sagittis commodo. Nullam a mauris ut massa accumsan luctus.",
        location = "D17",
        materials = listOf("Matériel 1", "Matériel 2", "Matériel 3"),
        available = false
    )
}