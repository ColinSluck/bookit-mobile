package com.diiage.bookit.ui.core.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.diiage.bookit.R
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Booking
import com.diiage.bookit.ui.core.functions.formatMaterialsList


@Composable
fun BookableCard(bookable: Bookable, booking: Booking? = null, onClick: () -> Unit) {
    Box (
        modifier = Modifier.padding(horizontal = 18.dp)
            .clickable { onClick() }

        ){
        Column {
            Box(
                Modifier
                    .clip(RoundedCornerShape(4.dp))
            ) {

                Image(
                    painter = if (bookable.images.isNullOrEmpty()) {
                        painterResource(id = R.drawable.bookable_placeholder)
                    } else {
                        rememberImagePainter(data = bookable.images[0], builder = { crossfade(true) })
                    },
                    contentDescription = "bookable image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(195.dp)
                )

                if (booking != null) {
                    Row(
                        Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                    ){
                        BookingDate(booking)
                    }
                }
            }

            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                ){
                Text(
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black,
                    text = bookable.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000)
                    )
                )
                Capacity(bookable.maxCapacity)
            }


            Row(
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
                    text = bookable.place,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000)
                    )
                )
            }

            if (bookable.materials?.isNotEmpty() == true) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.image10),
                        contentDescription = "Icône materials",
                        modifier = Modifier
                            .size(20.dp)
                    )

                    Text(
                        modifier = Modifier.padding(2.dp),
                        color = Color.Black,
                        text = formatMaterialsList(bookable.materials),
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
}
@Composable
fun Capacity(
    maxCapacity : Int
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
            text = maxCapacity.toString(),
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
    booking: Booking
) {

    // From 2023-10-18 to 18/10
    val formattedDate = booking.date.substring(8, 10) + "/" + booking.date.substring(5, 7)
    val formattedStartTime = booking.startTime?.substring(0, 5)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))

    ) {
        Row(
            Modifier.padding(7.dp)
        ){
            Text(text = if(formattedStartTime !=null) "Le $formattedDate à $formattedStartTime" else "Le $formattedDate",
                style = TextStyle(
                    color = Color(0xFF000000),
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400)
                )

            )

            Icon(
                painter = painterResource(id = R.drawable.image18),
                contentDescription = "Icône d'horloge",
                modifier = Modifier
                    .size(15.dp)
                    .padding(start = 4.dp)
            )
        }
    }
    
}