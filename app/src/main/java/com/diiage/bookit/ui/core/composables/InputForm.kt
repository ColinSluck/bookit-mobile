package com.diiage.bookit.ui.core.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.bookit.R

@Composable
fun InputForm(
    label: String,
    textState: MutableState<TextFieldValue>,
    characterLimit: Int? = null,
    isPasswordField: Boolean = false
) {
    val passwordVisibility = remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState.value,
            onValueChange = {
                if (!isPasswordField && (characterLimit == null || it.text.length <= characterLimit)) {
                    textState.value = it
                } else if (isPasswordField) {
                    textState.value = it
                }
            },
            label = { Text(
                text = label,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF457B9D)
                )
            ) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFFFFFFF),
                unfocusedIndicatorColor = Color(0xFF7A7A7A),
                focusedIndicatorColor = Color(0xFF457B9D),
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000)
            ),
            visualTransformation = if (isPasswordField && !passwordVisibility.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                if (isPasswordField) {
                    IconButton(onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisibility.value) R.drawable.eye else R.drawable.eye_blind
                            ),
                            contentDescription = if (passwordVisibility.value) "Hide Password" else "Show Password",
                            modifier = Modifier.size(32.dp).padding(4.dp)
                        )
                    }
                } else if (characterLimit != null) {
                    Text(
                        text = "${textState.value.text.length}/$characterLimit",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7A7A7A),
                        ),
                        modifier = Modifier.padding(top = 14.dp)
                    )
                }
            }
        )
    }
}


@Preview(name = "InputForm preview 1", showBackground = true)
@Composable
fun InputFormPreview1() {
    val nameState = remember { mutableStateOf(TextFieldValue("")) }

    InputForm("Adresse Email", nameState, 50)
}