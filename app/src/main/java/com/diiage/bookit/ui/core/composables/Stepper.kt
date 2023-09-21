package com.diiage.bookit.ui.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Stepper() {
    var currentStep by remember { mutableStateOf(0) }

    // Créez une liste de couleurs pour les étapes
    val stepColors = listOf(
        Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Créez une ligne de rectangles pour les étapes
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stepColors.forEachIndexed { index, color ->
                Box(
                    modifier = Modifier
                        .width(75.dp)
                        .height(10.dp)
                        .background(color = if (index <= currentStep) Color(0xFF457B9D) else color, shape = RoundedCornerShape(size = 5.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    // Vous pouvez ajouter du contenu personnalisé ici
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Boutons de navigation
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.weight(1f)
                    .padding(start = 16.dp, end = 8.dp)
                    .fillMaxWidth()
                    .clickable (enabled = currentStep > 0) {
                        if (currentStep > 0) {
                            currentStep--
                        }
                    },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                onClick = {
                    if (currentStep > 0) {
                        currentStep--
                    }
                },
                //enabled = currentStep > 0
            ) {
                Text(
                    text = "Retour",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF457B9D)),
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .width(215.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .background(color = Color(0xFF457B9D), shape = RoundedCornerShape(size = 5.dp)),
                onClick = {
                    if (currentStep < stepColors.size - 1) {
                        currentStep++
                    }
                },
                enabled = currentStep < stepColors.size - 1
            ) {
                Text(
                    text = "Suivant",
                    modifier = Modifier
                        .width(80.dp)
                        .height(24.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
        }
    }
}
