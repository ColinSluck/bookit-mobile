package com.diiage.bookit.ui.core.composables

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R


/**
 * Crée un bouton personnalisé avec des paramètres flexibles.
 *
 * @param text Le texte à afficher sur le bouton.
 * @param width La largeur du bouton. Peut être de type [Int] ou [Double].
 * @param height La hauteur du bouton. Peut être de type [Int] ou [Double].
 * @param colorButton La couleur de fond du bouton.
 * @param colorText La couleur du texte du bouton.
 * @param textSize La taille du texte du bouton.
 * @param borderColor La couleur de la bordure du bouton (facultatif, par défaut [Color.Transparent]).
 */
@Composable
fun BI_Button(text: String, width: Any, height: Any, colorButton: Color, colorText: Color, textSize: Int, fontWeight: Int, borderColor: Color? = null) {
    val context = LocalContext.current
    val w = when (width) {
        is Int -> width.toInt().dp
        is Double -> width.toDouble().dp
        else -> 0.dp // Valeur par défaut de 0 dp si la conversion échoue
    }

    val h = when (height) {
        is Int -> height.toInt().dp
        is Double -> height.toDouble().dp
        else -> 0.dp // Valeur par défaut de 0 dp si la conversion échoue
    }

    Button(
        onClick = {
            Toast.makeText(context, "Action réussi!", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .border(1.dp, borderColor ?: Color.Transparent, shape = RoundedCornerShape(size = 5.dp))
            .width(w)
            .height(h),
        colors = ButtonDefaults.outlinedButtonColors(colorButton),
        shape = RoundedCornerShape(size = 5.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = textSize.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(fontWeight),
                color = colorText,
                textAlign = TextAlign.Center
            )
        )
    }
}
