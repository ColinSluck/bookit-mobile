package com.diiage.bookit.ui.composables

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R

@Composable
fun QuickAnnouncement() {

    Row(
    ) {
        val context = LocalContext.current
       Button(
           onClick = {
               Toast.makeText(context, "L'annonce a bien été crée!", Toast.LENGTH_SHORT).show()
           },
           modifier = Modifier
               .width(340.dp)
               .height(177.dp),
           colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF)),
           shape = RoundedCornerShape(5.dp),
           border = BorderStroke(1.dp, Color(0xFF7A7A7A))
       ) {
           Box(
               Modifier
                   .weight(2f)
                   .fillMaxHeight()
           ){
               Column(
               ){
                   Text(
                       text = "Ajoutez votre annonce rapidement !",
                       style = TextStyle(
                           fontSize = 14.sp,
                           fontWeight = FontWeight(700),
                           color = Color(0xFF000000),
                       ),
                       modifier = Modifier
                           .padding(top = 11.dp, end = 79.dp)
                           .width(144.dp)
                           .height(63.dp)

                   )

                   Text(
                       text = "Ne perdez plus de temps et commencez à partager vos équipements avec vos collaborateurs !",
                       style = TextStyle(
                           fontSize = 12.sp,
                           fontWeight = FontWeight(400),
                           color = Color(0xFF7A7A7A),
                       ),
                       modifier = Modifier
                           .padding(top = 10.dp)
                           .width(170.dp)
                           .height(72.dp)
                   )

               }
           }
           Box(
               Modifier
                   .weight(1f)
                   .fillMaxHeight()
           ){
               Image(
                   painter = painterResource(id = R.drawable.image21),
                   contentDescription = "image description",
                   contentScale = ContentScale.FillBounds,
                   modifier = Modifier
                       .padding(start = 10.dp, end = 6.dp, top = 41.dp)
                       .width(75.dp)
                       .height(75.dp)
               )
           }
       }
    }

}

@Preview
@Composable
fun QuickAnnouncementPreview() {
    QuickAnnouncement()
}