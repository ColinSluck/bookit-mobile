package com.diiage.bookit.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.ui.core.composables.BI_Button
import com.diiage.bookit.ui.composables.PersonalInformation
import com.diiage.bookit.ui.composables.ProfileHeader
import com.diiage.bookit.ui.composables.QuickAnnouncement

private typealias UIState = ProfileState

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: ProfileViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    ProfileContent(
        state = state,
        handleAction = viewModel::handleAction
    )
}

@Composable
private fun ProfileContent(
    state: UIState = UIState(),
    handleAction: (ProfileAction) -> Unit
) {
    Box(
        Modifier.background(Color(0xFFFFFFFF)).verticalScroll(rememberScrollState())
    ){
        Column {
            ProfileHeader(lastnameValue = state.user.lastName, firstnameValue = state.user.firstName, createdAccountValue = state.user.createdAt)
            if(state.user.role == "Admin"){
                Row(
                    modifier = Modifier
                        .padding(horizontal = 45.dp, vertical = 36.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    QuickAnnouncement()
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
                    BI_Button("Déconnexion", 371, 45, Color(0xFFE63946), Color(0xFFFFFFFF), 16, 400, onClick = {handleAction(ProfileAction.OnDisconnect)})
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
