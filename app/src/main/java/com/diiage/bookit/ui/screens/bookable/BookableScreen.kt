package com.diiage.bookit.ui.screens.bookable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
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
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.composables.PreviewContent
import com.diiage.bookit.ui.core.composables.filter.SelectedDate
import com.diiage.bookit.ui.core.navigate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate

/**
 * Cette composable représente l'écran d'un "bookable". Elle affiche les détails d'un objet "bookable", y compris ses images,
 * son nom, sa disponibilité, sa description, ses spécifications et ses équipements. Les utilisateurs peuvent également réserver
 * cet "bookable" s'il est disponible.
 *
 * @param navController Le contrôleur de navigation pour la gestion de la navigation entre les écrans.
 * @param id L'identifiant unique de l'objet "bookable" à afficher.
 */

private typealias UIState = BookableState

/**
 * Main entry point for displaying bookable content.
 *
 * @param navController Controls and monitors navigation actions.
 * @param id The unique identifier for the bookable item.
 */
@Composable
fun BookableScreen(navController: NavController, id: String) {
    val viewModel: BookableViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(id) {
        viewModel.init(id = id)
        viewModel.events
            .onEach { event ->
                if (event is Destination.Bookings) {
                    navController.navigate(event)
                }
            }.collect()
    }

    BookableContent(
        state = state,
        handleAction = viewModel::handleAction
    )
}


/**
 * Displays the main content of the bookable screen.
 *
 * @param state The current UI state of the screen.
 * @param handleAction A lambda function to handle various actions/events.
 */
@Composable
fun BookableContent(
    state: UIState = UIState(),
    handleAction: (BookableAction) -> Unit
) {
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
                painter = if (state.initialImages.isNullOrEmpty()) {
                    painterResource(id = R.drawable.bookable_placeholder)
                } else {
                    rememberImagePainter(data = state.initialImages!![0], builder = { crossfade(true) })
                },
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
                state.initialImages?.drop(1)?.drop(0)?.forEachIndexed { index, image ->
                    Image(
                        painter = if (state.initialImages.isEmpty()) {
                            painterResource(id = R.drawable.bookable_placeholder_2) // Chargez l'image miniature par défaut depuis les ressources
                        } else {
                            rememberImagePainter(data = image, builder = { crossfade(true) })
                        },
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "",
                        modifier = Modifier
                            .width(85.dp)
                            .height(85.dp)
                            .clip(RoundedCornerShape(29.dp))
                            .clickable {
                                if (!state.initialImages.isNullOrEmpty()) {
                                    val updatedImages = state.initialImages.toMutableList()
                                    updatedImages[0] = image
                                    updatedImages[index + 1] = state.initialImages[0]
                                    handleAction(BookableAction.OnImageUpdate(updatedImages))
                                }
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
                text = "Spécifications :",
                modifier = Modifier.padding(PaddingValues(15.dp, 10.dp)),
                fontSize = 18.sp,
                //fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color.Black
            )

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(PaddingValues(15.dp, 10.dp))){
                Icon(
                    painter = painterResource(id = R.drawable.image8),
                    contentDescription = "Icône localisation",
                    modifier = Modifier
                        .size(25.dp)
                        //.padding(start = 1.dp)
                )
                Text(text = "${state.people} personnes maximum")
            }

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(PaddingValues(15.dp, 10.dp))){
                Icon(
                    painter = painterResource(id = R.drawable.image9),
                    contentDescription = "Icône localisation",
                    modifier = Modifier
                        .size(25.dp)
                        //.padding(start = 1.dp)
                )
                Text(text = state.location)
            }


            Text(
                text = "Equipements :",
                fontSize = 18.sp,
                modifier = Modifier.padding(PaddingValues(15.dp, 10.dp)),
                //fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                color = Color.Black,
            )

            state.materials.forEach {

                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(PaddingValues(15.dp, 10.dp))) {

                    Text(text = it)
                }
            }

            SelectedDate(state = state, handleAction = handleAction)

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
                onClick = { handleAction(BookableAction.OnBook) },
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

/**
 * Preview function for the `BookableContent` Composable.
 */
@Preview
@Composable
private fun BookablePreview() = PreviewContent {

}