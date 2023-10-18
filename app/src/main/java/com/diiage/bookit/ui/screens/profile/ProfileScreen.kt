package com.diiage.bookit.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.diiage.bookit.ui.composables.PersonalInformation
import com.diiage.bookit.ui.composables.ProfileHeader
import com.diiage.bookit.ui.composables.QuickAnnouncement
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.navigate
import com.diiage.bookit.ui.core.composables.BI_Button
import com.diiage.bookit.ui.core.functions.PopUp_Deconnection
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


private typealias UIState = ProfileState

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: ProfileViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.events
            .onEach { event ->
                if (event is NavigationEvent.NavigateToLogin)
                    navController.navigate(Destination.Login)
                else if (event is NavigationEvent.NavigateToCreateBookable)
                    navController.navigate(Destination.CreateBookable)
            }.collect()
    }

    ProfileContent(
        state = state,
        handleAction = viewModel::handleAction
    )
}

@Composable
public fun ProfileContent(
    state: UIState = UIState(),
    handleAction: (ProfileAction) -> Unit
) {
    Box(
        Modifier
            .background(Color(0xFFFFFFFF))
            .verticalScroll(rememberScrollState())
    ){
        Column {
            ProfileHeader(lastnameValue = state.user.lastName, firstnameValue = state.user.firstName, createdAccountValue = state.user.createdAt)
            if(state.user.role == "Admin"){
                Row(
                    modifier = Modifier
                        .padding(horizontal = 45.dp, vertical = 36.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    QuickAnnouncement(handleAction = handleAction)
                }
            }
            PersonalInformation(lastnameValue = state.user.lastName, firstnameValue = state.user.firstName, emailValue = state.user.email)
            Column(
                Modifier
                    .background(Color(0xFFFFFFFF))
            ){
                Row(
                    modifier = Modifier.padding(start = 30.dp, end = 29.dp, top = 36.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = { handleAction(ProfileAction.OnDisconnect) },
                        modifier = Modifier
                            .border(
                                1.dp,
                                Color.Transparent,
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                            .width(371.dp)
                            .height(45.dp),
                        colors = ButtonDefaults.outlinedButtonColors(Color(0xFFE63946)),
                        shape = RoundedCornerShape(size = 5.dp)
                    ) {
                        Text(
                            text = "Déconnexion",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(start = 30.dp, top = 11.dp, end = 29.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    BI_Button("Supprimer mon compte", 371, 45, Color.Transparent, Color(0xFF000000), 16, 400, Color(0xFFE63946))
                }
            }
        }
    }
}
