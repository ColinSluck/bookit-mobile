/**
 * Ce fichier contient plusieurs composables utilisés pour créer des pop-ups et des modales
 * dans une interface utilisateur Composable. Ces composables incluent des pop-ups de
 * confirmation, de succès d'invitation, d'invitations de collaborateurs, de demande de mot
 * de passe, etc.
 *
 * Les principaux composables de ce fichier sont :
 * - ConfirmationPopUp : Affiche un message de confirmation.
 * - InvitationSuccessPopUp : Affiche un message de succès d'invitation.
 * - AreYouSurePopUp : Demande une confirmation de l'utilisateur.
 * - PopUp_Deconnection : Affiche un pop-up de déconnexion.
 * - PopUp_DeleteAccount : Affiche un pop-up de suppression de compte.
 * - InviteCollabPopUp : Permet d'inviter des collaborateurs.
 * - PasswordDemand : Demande un mot de passe pour la confirmation.
 *
 * Le fichier utilise des variables d'état pour contrôler la visibilité des pop-ups
 * en fonction des interactions de l'utilisateur.
 */
package com.diiage.bookit.ui.core.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R


@Composable
fun ConfirmationPopUp(onCloseClick: () -> Unit) {
    Row (
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

    ){
        Column (

            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = "connexion image",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter

            )



            Text(
                text = "C'est dans la poche !",
                style = TextStyle
                    (
                    textAlign = TextAlign.Center,fontWeight = FontWeight.Bold
                ),
               modifier = Modifier
                 .padding(bottom = 5.dp, top = 5.dp)
            )

            Text(
                text = "Votre réservation est confirmée ! Vous pouvez la retrouver dans l’onglet “Mes réservations” pour inviter vos collaborateurs.",
                style = TextStyle
                    (
                    color = Color(0xFF7A7A7A),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )

            CloseButton(onCloseClick)



        }

    }

}


@Composable
fun InvitationSuccessPopUp(onCloseClick: () -> Unit) {
    Row (
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column (

            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = R.drawable.img_2),
                contentDescription = "invitation image",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter

            )



            Text(
                text = "Invitation envoyée !",
                style = TextStyle
                    (
                    textAlign = TextAlign.Center,fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 5.dp)
            )

            CloseButton(onCloseClick)

        }

    }

}

@Composable
fun AreYouSurePopUp(onCloseClick: () -> Unit) {
    Row (
        modifier = Modifier
            .background(Color.White)
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
        ){


        Column (
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Êtes-vous sûr ?",
                style = TextStyle(fontSize = 22.sp,fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top=10.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )

            Row (horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start=10.dp)
                    .fillMaxWidth()){
                Text(text = "Salle de réunion D17",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .height(140.dp
                    )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(end=0.dp)
                        .fillMaxWidth()){
                    Image(
                        painter = painterResource(id = R.drawable.img_3),
                        contentDescription = "Localisation icon",
                        modifier = Modifier
                            .size(width = 20.dp, height = 20.dp)
                    )

                    Text( text = "1er étage",
                        style = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .padding(start = 2.dp)


                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(end=0.dp)
                        .fillMaxWidth()){
                    Image(
                        painter = painterResource(id = R.drawable.img_4),
                        contentDescription = "Person numbers",
                        modifier = Modifier
                            .size(width = 20.dp, height = 20.dp)
                    )

                    Text( text = "6",
                        style = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .padding(start = 2.dp)


                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(end=0.dp)
                        .fillMaxWidth()){
                    Image(
                        painter = painterResource(id = R.drawable.img_5),
                        contentDescription = "Date",
                        modifier = Modifier
                            .size(width = 20.dp, height = 20.dp)
                    )

                    Text( text = "12 sept. 2023",
                        style = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .padding(start = 2.dp)


                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(end=0.dp)
                        .fillMaxWidth()){
                    Image(
                        painter = painterResource(id = R.drawable.img_6),
                        contentDescription = "Hour",
                        modifier = Modifier
                            .size(width = 20.dp, height = 20.dp)
                    )

                    Text( text = "10:30 - 11:00",
                        style = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .padding(start = 2.dp)


                    )
                }
            }


            ConfirmButton(onCloseClick)
            ReturnButton(onCloseClick)
        }
    }
}


@Composable
fun InviteCollabPopUp(onCloseClick: () -> Unit) {

    Row (
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){

        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ){
            Text(text = "Inviter vos collaborateurs",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 22.sp,fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth())

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )

            Text(text = "Email",
                textAlign = TextAlign.Left,
                style = TextStyle(fontSize = 15.sp,fontWeight = FontWeight.SemiBold),
                modifier = Modifier
                    .padding(top = 50.dp, start = 10.dp)
                    .fillMaxWidth())

            InputField()


            ValidButton(onCloseClick)


        }
    }
}


@Composable
fun AreYouSureCancelPopUp(onCloseClick: () -> Unit, onValidateClick: () -> Unit) {
    Row (
        modifier = Modifier
            .background(Color.White)
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){

        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(text = "Êtes-vous sûr ?",
                style = TextStyle(fontSize = 22.sp,fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top=10.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )



            Image(
                painter = painterResource(id = R.drawable.img_8),
                contentDescription = "invitation image",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .padding(top = 50.dp)

            )


            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .height(150.dp)
            ) {
                YesButton(
                    onCloseClick = {onCloseClick()},
                    onValidateClick = {onValidateClick()})


                NoButton(onCloseClick)
            }


        }
    }
}


@Composable
fun PasswordDemand(onCloseClick: () -> Unit) {

    Row (
        modifier = Modifier
            .background(Color.White)
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Entrez votre mot de passe pour confirmer",
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
                modifier = Modifier
                    .padding(top = 10.dp)
            )


            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )

            Column (verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .height(300.dp)
            ){
                Text(text = "Mot de passe",
                    style = TextStyle(fontSize = 15.sp,fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Left),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 10.dp)
                        .fillMaxWidth())

                PasswordTextField()

                SupprimAccountButton(onCloseClick)

                CancelButton(onCloseClick)
            }



        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("********") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, start = 12.dp, bottom = 20.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF7A7A7A),
                shape = RoundedCornerShape(size = 5.dp)
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField() {
    var text by remember { mutableStateOf("") }



    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },

        label = { Text("lucas.martin@diiage.org") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF7A7A7A),
                shape = RoundedCornerShape(size = 5.dp)
            )
    )
}

@Composable
fun CloseButton(onCloseClick: () -> Unit) {
    Button(
        onClick = onCloseClick,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color(red = 69, green = 123, blue = 157)),
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(

            Color.White
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Fermer", color =  Color(red = 69, green = 123, blue = 157) )
            }
        }
    )
}



@Composable
fun SupprimAccountButton(onCloseClick: () -> Unit) {
    var isComponentVisible by remember { mutableStateOf(false) }
    Button(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onCloseClick,
        colors = ButtonDefaults.buttonColors(
            Color(0xFF7A7A7A)
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Supprimer mon compte", color = Color.White, style = TextStyle(fontWeight = FontWeight.SemiBold) )

            }
        }
    )

}

@Composable
fun CancelButton(onCloseClick: () -> Unit) {
    var isComponentVisible by remember { mutableStateOf(false) }
    Button(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color(red = 69, green = 123, blue = 157)),
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onCloseClick,
        colors = ButtonDefaults.buttonColors(
            Color.White
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Annuler", color = Color(red = 69, green = 123, blue = 157), style = TextStyle(fontWeight = FontWeight.SemiBold) )

            }
        }
    )
}


@Composable
fun NoButton(onCloseClick: () -> Unit) {
    var isComponentVisible by remember { mutableStateOf(false) }
    Button(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color(red = 69, green = 123, blue = 157)),
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onCloseClick,
        colors = ButtonDefaults.buttonColors(
            Color.White
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Non", color = Color(red = 69, green = 123, blue = 157), style = TextStyle(fontWeight = FontWeight.SemiBold) )

            }
        }
    )
}

@Composable
fun YesButton(onCloseClick: () -> Unit,onValidateClick: () -> Unit) {

    Button(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = { onValidateClick() },
        colors = ButtonDefaults.buttonColors(
            Color(0xFFE63946)
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Oui", color = Color.White, style = TextStyle(fontWeight = FontWeight.SemiBold) )

            }
        }
    )
}


@Composable
fun ConfirmButton(onCloseClick: () -> Unit) {
    var isComponentVisible by remember { mutableStateOf(false) }
    Button(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onCloseClick,
        colors = ButtonDefaults.buttonColors(
            Color(red = 69, green = 123, blue = 157)
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Confirmer", color = Color.White )
            }
        }
    )
}

@Composable
fun ValidButton(onCloseClick: () -> Unit) {
    var isComponentVisible by remember { mutableStateOf(false) }
    Button(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onCloseClick,
        colors = ButtonDefaults.buttonColors(
            Color(red = 69, green = 123, blue = 157)
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Valider", color = Color.White )
            }
        }
    )
}

@Composable
fun ReturnButton(onCloseClick: () -> Unit) {
    var isComponentVisible by remember { mutableStateOf(false) }
    Button(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color(red = 69, green = 123, blue = 157)),
        modifier = Modifier
            .padding(bottom = 3.dp)
            .width(367.dp)
            .height(60.dp),
        onClick = onCloseClick,
        colors = ButtonDefaults.buttonColors(

            Color.White
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Retour", color =  Color(red = 69, green = 123, blue = 157) )
            }
        }
    )
}


@Preview
@Composable
fun Preview3() {
    ConfirmationPopUp(onCloseClick = {})
}

@Preview
@Composable
fun Preview4() {
    InvitationSuccessPopUp(onCloseClick = {})
}

@Preview
@Composable
fun Preview5() {
    AreYouSurePopUp(onCloseClick = {})
}

@Preview
@Composable
fun Preview6() {
    InviteCollabPopUp(onCloseClick = {})
}

@Preview
@Composable
fun Preview7() {
    AreYouSureCancelPopUp(onCloseClick = {}, onValidateClick = {})
}

@Preview
@Composable
fun Preview8() {
    PasswordDemand(onCloseClick = {})
}



