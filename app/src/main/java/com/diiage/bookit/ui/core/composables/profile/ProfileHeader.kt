package com.diiage.bookit.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.composables.Line
import com.diiage.bookit.ui.core.functions.calculateAccountAge
import com.diiage.bookit.ui.core.functions.generateDefaultProfilePicture

@Composable
fun ProfileHeader(lastnameValue: String, firstnameValue: String, createdAccountValue: String) {
    Column(
        Modifier.background(color = Color(0xFFFFFFFF))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 28.dp)
        ) {
            Text(
                text = "PROFIL",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier.padding(start = 39.dp)
            )
        }

        val context = LocalContext.current
        val bitmap = generateDefaultProfilePicture("$firstnameValue $lastnameValue", 69, 69)
        val imageBitmap = rememberUpdatedState(bitmap)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 39.dp)
        ) {
            Image(
                bitmap = imageBitmap.value.asImageBitmap(),
                contentDescription = "Profile Picture",
                Modifier
                    .padding(1.dp)
                    .width(69.dp)
                    .height(69.dp)
            )
            Column {
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$firstnameValue $lastnameValue",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                        )
                    )
                    val accountAge = calculateAccountAge(createdAccountValue)
                    Text(
                        text = "Membre depuis $accountAge",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7A7A7A),
                        )
                    )
                }
            }
        }
        Line(leftValue = 63, topValue = 30, rightValue = 62, bottomValue = 28, widthValue = 305)
    }
}



@Preview
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader(lastnameValue = "MARTIN", firstnameValue = "Lucas", createdAccountValue = "2020-09-05T07:15:58.774737")
}