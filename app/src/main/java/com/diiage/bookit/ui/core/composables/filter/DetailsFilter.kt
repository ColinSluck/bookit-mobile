package com.diiage.bookit.ui.core.composables.filter

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.composables.picker.NumberPicker

@Composable
fun DetailsFilter() {
    Box(
        Modifier.background(Color.White)
    ){
        Row{
            Text(
                text = "Capacité minimale",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 21.dp, start = 105.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                NumberPicker()
                Text(
                    text = "personnes",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF457B9D),
                    ),
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
        }
        Row(
            Modifier.padding(top = 60.dp)
        ) {
            Column {
                Text(
                    text = "Équipement",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier.padding(top = 72.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(top = 166.dp),

        ) {
            val mItemsList: List<String> = listOf("Machine à café", "Tableau blanc", "Télévision", "Gel hydroalcoolique")
            mItemsList.forEach { items ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.absoluteOffset(0.dp, (-12).dp)
                ) {
                    val isChecked = remember { mutableStateOf(false) }

                    Checkbox(
                        checked = isChecked.value,
                        onCheckedChange = { isChecked.value = it },
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF457B9D),
                            uncheckedColor = Color(0xFF457B9D),
                            checkmarkColor = Color.White
                        )
                    )
                    Text(text = items)
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsFilterPreview() {
    DetailsFilter()
}