package com.diiage.bookit.ui.core.composables.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.diiage.bookit.R
import com.diiage.bookit.ui.core.composables.Line
import com.diiage.bookit.ui.core.composables.picker.HoursPickerEnd
import com.diiage.bookit.ui.core.composables.picker.HoursPickerStart
import com.diiage.bookit.ui.screens.filter.FilterAction
import com.diiage.bookit.ui.screens.filter.FilterState


@Composable
fun SelectedDate(
    state: FilterState,
    handleAction: (FilterAction) -> Unit
    ) {
    Box(
        Modifier
            .background(Color.White, RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = Color(0x807A7A7A),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Column {
            Text(
                text = "Sélectionner une date",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF000000),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(700)
                ),
                modifier = Modifier.padding(start = 100.dp, top = 21.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
                ) {
                    WheelDatePicker(
                        rowCount = 1,
                        size = DpSize(300.dp, 36.dp),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(700)
                        ),
                        textColor = Color(0xFF457B9D),
                        selectorProperties = WheelPickerDefaults.selectorProperties(
                            enabled = false,
                        ),
                    ) { snappedDate -> }
                }
            }
            Line(leftValue = 28, topValue = 0, rightValue = 28, bottomValue = 0, widthValue = 327)
            Row(
                Modifier.padding(start = 74.dp, top = 22.dp, bottom = 12.dp)
            ) {
                Column {
                    Row {
                        Text(
                            text = "De",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF7A7A7A)
                            )
                        )
                    }
                    Row {
                        //HoursPickerStart(state, handleAction)
                    }
                }
                Column(
                    Modifier.padding(start = 52.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = "right arrow",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(46.dp)
                    )
                }
                Column(
                    Modifier.padding(start = 49.dp)
                ) {
                    Text(
                        text = "À",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7A7A7A)
                        )
                    )
                    //HoursPickerEnd(state, handleAction)
                }
            }
        }
    }
}