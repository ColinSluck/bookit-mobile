package com.diiage.bookit.ui.core.composables.bookings

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*
import com.diiage.bookit.R

@Composable
fun CompactBookable(
    title: String,
    location: String,
    materials: String,
    date: Date,
    image: ImageBitmap = ImageBitmap.imageResource(R.drawable.bookable_placeholder),
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale = animateFloatAsState(if (isPressed) 0.95f else 1f, label = "").value

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(112.dp)
            .width(334.dp)
            .padding(0.dp, 12.dp, 0.dp, 12.dp)
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            bitmap = image,
            contentScale = ContentScale.FillBounds,
            contentDescription = "Bookable image",
            modifier = Modifier
                .height(92.dp)
                .width(92.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column{
            Text(
                text = title,
                color = Color.Black,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(700),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = location,
                color = Color(0xFF7A7A7A),
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 15.sp
            )

            Text(
                text = materials,
                color = Color(0xFF7A7A7A),
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 15.sp
            )

            Text(
                text = SimpleDateFormat("dd MMM yyyy", Locale.FRANCE).format(date),
                color = Color(0xFF7A7A7A),
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 15.sp
            )
        }
    }
}

@Preview(name = "CompactBookable preview 1", showBackground = true)
@Composable
fun CompactBookablePreview1() {
    CompactBookable(
        title = "Salle de réunion D17",
        location = "1er étage",
        materials = "Vidéoprojecteur, Paperboard",
        date = Date(),
        onClick = {}
    )
}

@Preview(name = "CompactBookable preview 2", showBackground = true)
@Composable
fun CompactBookablePreview2() {
    CompactBookable(
        title = "Salle de réunion D17 vdsvdsvl,msd,vmldsvmlsd,vmld,svmslv,dslmv,dsmv",
        location = "1er étage dvsdvdùvksdùkvlsdvsdmvlksdlmvsdv",
        materials = "Vidéoprojecteur, Paperboard dvlskvmldsvlmsdkvmdlskvlmdskvlmdsvs",
        date = Date(),
        onClick = {}
    )
}