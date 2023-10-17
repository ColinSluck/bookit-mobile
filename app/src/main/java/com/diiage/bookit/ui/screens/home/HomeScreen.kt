package com.diiage.bookit.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.R
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Booking
import com.diiage.bookit.domain.models.Material
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.composables.BookableCard
import com.diiage.bookit.ui.core.composables.search.SearchBar

private typealias UIState = HomeState

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state,
        navController = navController
    )
}
@Composable
private fun HomeContent(
    state: UIState = UIState(),
    navController: NavController
){
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ){
        SearchBar(
            firstName = state.firstName,
            onClick = {
                navController.navigate(Destination.Filter.route) {
                    launchSingleTop = true
                }
            },
        )
        Row(
            modifier = Modifier
                .padding(top = 195.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier
                    .padding(horizontal = 79.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Box(

                    Modifier
                        .weight(1f)
                ){
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.image5),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                        }
                        Row {
                            Text(
                                text = "Café",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF7A7A7A)
                                )
                            )
                        }
                    }
                }
                Box(
                    Modifier
                        .weight(1f)
                ){
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.image6),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                        }
                        Row {
                            Text(
                                text = "Salles",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF7A7A7A)
                                )
                            )
                        }
                    }
                }
                Box(
                    Modifier
                        .weight(1f)
                ){
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.image7),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                        }
                        Row {
                            Text(
                                text = "Voitures",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF7A7A7A)
                                )
                            )
                        }
                    }
                }
            }
        }
        Row(
            Modifier
                .padding(top = 277.dp, start = 39.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "VOS RÉSERVATIONS",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000)
                )
            )

            Image(
                painter = painterResource(id = R.drawable.iconarrowleft),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 67.dp)
                    .width(43.dp)
                    .height(24.dp)
            )
        }
        Column(
            Modifier.padding(top = 344.dp)
        ){

            Row(
                Modifier
                    .padding(bottom = 42.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ){
                    BookableCard(
                        Bookable(
                            1,
                            "", "",
                            "Salle de Réunion D17", "",
                            "1er étage", 6,
                            2,
                            emptyList(),
                            listOf(
                                Material(id = 1, libelle = "Machine à café", bookableTypeId = 2, createdAt = "2023-10-01", updatedAt = "2023-10-01"),
                                Material(id = 2, libelle = "Tableau blanc", bookableTypeId = 2, createdAt = "2023-10-02", updatedAt = "2023-10-02"),
                                Material(id = 3, libelle = "Chaise", bookableTypeId = 2, createdAt = "2023-10-03", updatedAt = "2023-10-03")
                            ),
                        ),
                        Booking(1, 1, 2, "13/07", "10h30"),
                        handleAction = {}

                    )
                }
            }
            Row(
                Modifier
                    .padding(bottom = 42.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ){
                    BookableCard(
                        Bookable(
                            1,
                            "", "",
                            "Salle de Réunion D17", "",
                            "1er étage", 6,
                            2,
                            emptyList(),
                            listOf(
                                Material(id = 1, libelle = "Machine à café", bookableTypeId = 2, createdAt = "2023-10-01", updatedAt = "2023-10-01"),
                                Material(id = 2, libelle = "Tableau blanc", bookableTypeId = 2, createdAt = "2023-10-02", updatedAt = "2023-10-02"),
                                Material(id = 3, libelle = "Chaise", bookableTypeId = 2, createdAt = "2023-10-03", updatedAt = "2023-10-03")
                            ),
                        ),
                        Booking(1, 1, 2, "13/07", "10h30"),
                        handleAction = {}
                    )
                }
            }
        }
    }
}

