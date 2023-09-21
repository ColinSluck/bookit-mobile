package com.diiage.bookit.ui.core.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import java.time.format.DateTimeFormatter


@Composable
fun bookableView(
                    bookableName : String,
                    bookableLoc : String,
                    bookableOptions : String,
                    bookingDate: String,
                    bookingTime: String,
                    personNumber : String

) {

    Box (

    ){
        Column( // Colone qui contient : L'image(box), le nom(ligne1), Localisation(ligne2), Options(ligne3)
        ) {

            Box(
                Modifier
                    .clip(RoundedCornerShape(4.dp))
            ) {//box pour l'image

                Image(
                    painter = painterResource(id = R.drawable.rectangle8),
                    contentDescription = "bookable image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(353.dp)
                        .height(195.dp)
                )
                Row(
                    Modifier
                        .padding(start = 229.dp, top = 7.dp)
                ){
                    BookingDate(bookingDate = bookingDate, bookingTime = bookingTime)
                }
            }

            Row(//ligne1 pour le nom
            ) {
                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black,
                    text = bookableName,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000)
                    )
                )

                Column( //colonne qui contient un ligne pour afficher le nombre de participants
                    modifier = Modifier.padding(start = 166.dp)
                ) {
                    PersonsNumber(
                        personNumber = personNumber
                    )
                }
            }


            Row(
                //Ligne2 pour la localisation
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.image9),
                    contentDescription = "Icône Localisation",
                    modifier = Modifier
                        .size(20.dp)
                )

                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black,
                    text = bookableLoc,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000)
                    )
                )
            }

            Row(
                //ligne3 pour les options
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.image10),
                    contentDescription = "Icône Localisation",
                    modifier = Modifier
                        .size(20.dp)
                )

                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black,
                    text = bookableOptions,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000)
                    )
                )
            }

        }

    }
}
@Composable
fun PersonsNumber(//composable pour le nombre de participants
    personNumber : String

) {
    Row {

        Icon(
            painter = painterResource(id = R.drawable.image8),
            contentDescription = "Icône d'utilisateur",
            tint = Color.Black,
            modifier = Modifier
                .size(25.dp)
        )

        Text(
            text = personNumber,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color.Black,
            )
        )
    }

}


@Composable
fun BookingDate(
    bookingDate: String,
    bookingTime: String,
) {
   // val currentDate = LocalDate.now()
    //val formattedDate = bookingDate.format(DateTimeFormatter.ofPattern("dd/MM"))
    val formattedDate = bookingDate


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))

    ) {
        Row(
            Modifier.padding(7.dp)
        ){
            Text(text = "Le $formattedDate à $bookingTime",
                style = TextStyle(
                    color = Color(0xFF000000),
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400)
                )

            )

            Icon(
                painter = painterResource(id = R.drawable.image18),
                contentDescription = "Icône d'utilisateur",
                modifier = Modifier
                    .size(15.dp)
                    .padding(start = 1.dp)
            )
        }
    }
    
}

@Preview
@Composable
fun Preview1() {
    bookableView(bookableName = "Salle de réunion D17", bookableLoc ="1er étage" , bookableOptions = "Tableau blanc, machine à café...", personNumber = "6", bookingDate = "14/09", bookingTime = "10:30")
}

@Preview
@Composable
fun Preview2() {
    BookingDate("14/09", "10:30")
}
