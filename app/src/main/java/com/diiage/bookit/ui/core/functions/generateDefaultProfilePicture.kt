package com.diiage.bookit.ui.core.functions

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import java.util.Locale

fun generateDefaultProfilePicture(name: String, width: Int, height: Int): Bitmap {
    val resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(resultBitmap)

    // Choisissez une couleur d'arrière-plan. Vous pouvez également générer une couleur aléatoire ou basée sur le nom.
    val bgColor: Int = Color.LTGRAY
    val bgPaint = Paint()
    bgPaint.color = bgColor
    canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), bgPaint)

    // Dessinez la première lettre du nom
    val firstChar = name.uppercase(Locale.getDefault())[0]
    val textPaint = Paint()
    textPaint.color = Color.WHITE
    textPaint.textSize = (width / 2).toFloat() // Ajustez la taille du texte selon vos besoins
    textPaint.textAlign = Paint.Align.CENTER
    textPaint.isAntiAlias = true

    // Centrer le texte dans le Bitmap
    val xPos: Int = canvas.width / 2
    val yPos: Float = canvas.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2
    canvas.drawText(firstChar.toString(), xPos.toFloat(), yPos, textPaint)
    return resultBitmap
}