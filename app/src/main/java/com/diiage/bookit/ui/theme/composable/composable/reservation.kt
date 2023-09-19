package com.diiage.bookit.ui.theme.composable.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
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

            modifier = Modifier
                .background(Color.White)
                .padding(3.dp),

        ) {

            Box(
                Modifier
                    .clip(RoundedCornerShape(4.dp))
            ) {//box pour l'image

                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "bookable image",
                    contentScale = ContentScale.Crop,
                )

                    BookingDate(bookingDate = bookingDate, bookingTime = bookingTime)
            }

            Row(//ligne1 pour le nom
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black,
                    text = bookableName,
                    style = TextStyle(fontSize = 8.sp)
                )

                Column( //colonne qui contient un ligne pour afficher le nombre de participants
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    PersonsNumber(
                        personNumber = personNumber,
                    )
                }
            }


            Row(
                //Ligne2 pour la localisation
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Icône Localisation",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(9.dp)
                )

                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black,
                    text = bookableLoc,
                    style = TextStyle(fontSize = 5.sp)
                )
            }

            Row(
                //ligne3 pour les options
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Icône Localisation",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(9.dp)
                )

                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black,
                    text = bookableOptions,
                    style = TextStyle(fontSize = 5.sp)
                )
            }

        }

    }
}
@Composable
fun PersonsNumber(//composable pour le nombre de participants
    personNumber : String,
    modifier: Modifier = Modifier

) {
    Row {

        Icon(imageVector = Icons.Default.Person,
            contentDescription = "Icône d'utilisateur",
            tint = Color.Black,
            modifier = Modifier
                .size(12.dp)
        )

        Text(
            text = personNumber,
            style = TextStyle(
                fontSize = 8.sp,
                color = Color.Black,
            )
        )
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingDate(
    bookingDate: String,
    bookingTime: String,
) {
   // val currentDate = LocalDate.now()
    val formattedDate = bookingDate.format(DateTimeFormatter.ofPattern("dd/MM"))


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
           .padding(start = 63.dp, top = 5.dp)
           .height(7.dp)
           .clip(RoundedCornerShape(2.dp))

    ) {


        Text(text = "Le $formattedDate à $bookingTime",
            style = TextStyle(
                Color.Black,
                fontSize = 4.sp ),
            modifier = Modifier
                .background(Color.White)
                .height(8.dp)
                .padding(1.dp)

        )

        Icon(imageVector = Icons.Default.DateRange,
            contentDescription = "Icône d'utilisateur",
            tint = Color.Black,
            modifier = Modifier
                .size(8.dp)
                .background(Color.White)
        )
    }
    
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Preview1() {
    bookableView(bookableName = "Salle de réunion D17", bookableLoc ="1er étage" , bookableOptions = "Tableau blanc, machine à café...", personNumber = "6", bookingDate = "14/09", bookingTime = "10:30")
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Preview2() {
    BookingDate("14/09", "10:30")
}
