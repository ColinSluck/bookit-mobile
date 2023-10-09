package com.diiage.bookit.ui.screens.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.bookit.ui.core.composables.BI_Button
import com.diiage.bookit.ui.composables.PersonalInformation
import com.diiage.bookit.ui.composables.ProfilHeader
import com.diiage.bookit.ui.composables.QuickAnnouncement
import com.diiage.bookit.ui.core.composables.PreviewContent

private typealias UIState = ProfilState

@Composable
fun ProfilScreen(navController: NavController) {
    val viewModel: ProfilViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    ProfilContent(
        state = state
    )
}

@Composable
private fun ProfilContent(
    state: UIState = UIState(),
) {
    val role = state.role
    Box(
        Modifier.background(Color(0xFFFFFFFF))
    ){
        Column {
            ProfilHeader(lastnameValue = "Logan", firstnameValue = "ANGUENOT", createdAccountValue = "2023-09-05T07:15:58.774737")
            role?.let{
                Row(
                    modifier = Modifier
                        .padding(horizontal = 45.dp, vertical = 36.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    QuickAnnouncement()
                }
            }
            PersonalInformation(lastnameValue = "Logan", firstnameValue = "ANGUENOT", emailValue = "logan.anguenot@diiage.org")
            Column(
                Modifier
                    .height(396.dp)
                    .background(Color(0xFFFFFFFF))
            ){
                Row(
                    modifier = Modifier.padding(start = 30.dp, end = 29.dp, top = if (role !=null) 47.dp else 270.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    BI_Button("Déconnexion", 371, 45, Color(0xFFE63946), Color(0xFFFFFFFF), 16, 400)
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

@Preview
@Composable
fun ProfilPreview() = PreviewContent {
    ProfilContent()
}