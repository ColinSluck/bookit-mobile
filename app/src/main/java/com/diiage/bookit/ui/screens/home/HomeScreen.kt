package com.diiage.bookit.ui.screens.home

import android.widget.ScrollView
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.composables.bookableView

private typealias UIState = HomeState


@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state
    )
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun HomeContent(
    state: UIState = UIState()
){
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ){
        Text(
            text = "Bienvenue Logan !",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000)
            ),
            modifier = Modifier
                .padding(start = 39.dp, top = 50.dp)
        )
        val context = LocalContext.current
        Button(
            onClick = {
                Toast.makeText(context, "Action réussi!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
            shape = RoundedCornerShape(29.dp),
            border = BorderStroke(1.dp, Color(0x80000000)),
            modifier = Modifier
                .padding(top = 95.dp, start = 23.dp, end = 23.dp)
                .width(384.dp)
                .height(68.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image14),
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
            ){
                Row(
                    modifier = Modifier
                        .padding(start = 36.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Text(
                        text = "Réservez maintenant !",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000)
                        )
                    )
                }
                Row(
                    Modifier
                        .padding(start = 34.dp)
                ){
                    Text(
                        text = "Une salle de réunion, une voiture...",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7A7A7A)
                        )
                    )
                }
            }
        }
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
                    bookableView(
                        bookableName = "Salle de Réunion D17",
                        bookableLoc = "1er étage",
                        bookableOptions = "Tableau blanc, machine à café...",
                        bookingDate = "13/07",
                        bookingTime = "10h30",
                        personNumber = "6"
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
                    bookableView(
                        bookableName = "Salle de Réunion D17",
                        bookableLoc = "1er étage",
                        bookableOptions = "Tableau blanc, machine à café...",
                        bookingDate = "13/07",
                        bookingTime = "10h30",
                        personNumber = "6"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview(){
    HomeContent()
}