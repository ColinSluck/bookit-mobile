package com.diiage.bookit.ui.core.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyScreen() {

    var isSuccessPopupVisible by remember { mutableStateOf(false) }
    var isConfirmationPopupVisible by remember { mutableStateOf(false) }
    var isAreYouSureOne by remember { mutableStateOf(false) }
    var isAreYouSureTwo by remember { mutableStateOf(false) }
    var isConfirmSuppAccount by remember { mutableStateOf(false) }
    var isInviteCollab by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row (
            horizontalArrangement = Arrangement.SpaceEvenly){
            Button(
                onClick = { isSuccessPopupVisible = true },
            ) {
                Text("Succès invitation")
            }


        }

        Row( horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { isConfirmationPopupVisible = true },

                ) {
                Text("Confirmation")

            }
        }

        Row( horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { isAreYouSureOne = true },

                ) {
                Text("Êtes vous sûr ?")
            }
        }

        Row( horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { isAreYouSureTwo = true },

                ) {
                Text("Suppresion compte")
            }
        }



        Row( horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { isInviteCollab = true },

                ) {
                Text("Inviter collabrateurs")
            }
        }


        AnimatedPopup(
            isVisible = isSuccessPopupVisible,
            onDismiss = { isSuccessPopupVisible = false }
        ) {
            InvitationSuccessPopUp {
                isSuccessPopupVisible = false // Logique de fermeture du pop-up
            }
        }


        AnimatedPopup(
            isVisible = isConfirmationPopupVisible,
            onDismiss = { isConfirmationPopupVisible = false }
        ) {
            ConfirmationPopUp {
                isConfirmationPopupVisible = false // Logique de fermeture du pop-up
            }
        }


        AnimatedPopup(
            isVisible = isAreYouSureOne,
            onDismiss = { isAreYouSureOne = false }
        ) {
            AreYouSurePopUp {
                isAreYouSureOne = false // Logique de fermeture du pop-up
            }
        }

        AnimatedPopup(
            isVisible = isAreYouSureTwo,
            onDismiss = { isAreYouSureTwo = false }
        ) {
            AreYouSureCancelPopUp(
                onCloseClick = {isAreYouSureTwo = false},
                onValidateClick = {isAreYouSureTwo = false
                                    isConfirmSuppAccount = true}
            )
        }


        AnimatedPopup(
            isVisible = isConfirmSuppAccount,
            onDismiss = { isConfirmSuppAccount = false }
        ) {
            PasswordDemand {
                isConfirmSuppAccount = false 
            }
        }

        AnimatedPopup(
            isVisible = isInviteCollab,
            onDismiss = { isInviteCollab = false }
        ) {
            InviteCollabPopUp {
                isInviteCollab = false 
            }
        }


    }
}


@Composable
fun AnimatedPopup(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        exit = slideOutVertically(targetOffsetY = { -it }) // Animation de sortie
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyScreen() {
    MyScreen()
}