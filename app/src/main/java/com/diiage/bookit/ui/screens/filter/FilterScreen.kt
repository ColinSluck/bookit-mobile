package com.diiage.bookit.ui.screens.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.Screen
import com.diiage.bookit.ui.core.composables.Line
import com.diiage.bookit.ui.core.composables.filter.DetailsFilter
import com.diiage.bookit.ui.core.composables.filter.SelectedDate

private typealias UIState = FilterState

@Composable
fun FilterScreen(navController: NavController) {
    val viewModel: FilterViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    FilterContent(
        state = state,
        navController = navController,
        handleAction = viewModel::handleAction
    )
}

@Composable
fun FilterContent(
    state: UIState = UIState(),
    navController: NavController,
    handleAction: (FilterAction) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color(0x80898989))
            .padding(top = 39.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(topStart =  12.dp, topEnd = 12.dp))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 26.dp, top = 11.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ){
                    Image(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Close",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clickable { navController.popBackStack() }
                    )
                    Text(
                        text = "Filtres",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.padding(start = 127.dp)
                    )
                }
            }
            Line(leftValue = 0, topValue = 57, rightValue = 0, bottomValue = 0, widthValue = 430)
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 80.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 24.dp)
                ){
                    SelectedDate(state, handleAction)
                }
                Row(
                    modifier = Modifier.padding(top = 34.dp,  start = 26.dp)
                ) {
                    DetailsFilter(state, handleAction)
                }
                Divider(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .background(color = Color(0x807A7A7A)),
                    color = Color(0x807A7A7A),
                    thickness = 1.dp
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 20.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                   Column (
                       horizontalAlignment = Alignment.CenterHorizontally,
                   ){
                       Button(
                           onClick = {
                                    handleAction(FilterAction.ResetFilters)
                               },
                           colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White),
                           border = BorderStroke(1.dp, Color(0xFF457B9D)),
                           modifier = Modifier
                               .height(70.dp)
                               .width(138.dp)
                       ){
                           Text(
                               text = "Effacer",
                               style = TextStyle(
                                   fontSize = 16.sp,
                                   fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                   fontWeight = FontWeight(400),
                                   color = Color(0xFF000000),
                               )
                           )
                       }
                   }
                    Column (

                    ){
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFF457B9D)),
                            border = BorderStroke(1.dp, Color(0xFF457B9D)),
                            modifier = Modifier
                                .height(70.dp)
                        ){
                            Text(
                                text = "Afficher les annonces",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFFFFFFF),
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}