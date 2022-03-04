package com.stn.cocoatalk.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.stn.cocoatalk.ui.theme.TextWhite

@Composable
fun StandardTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val isPassword by remember {
        mutableStateOf(keyboardType == KeyboardType.Password)
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    TextField(
        value = text,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                color = TextWhite,
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        trailingIcon = {
            val image = if (passwordVisibility)
                Icons.Filled.Visibility
            else
                Icons.Filled.VisibilityOff
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                if (isPassword)
                    Icon(imageVector = image, contentDescription = "")
            }
        },
        visualTransformation = if (isPassword) {
            if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        } else VisualTransformation.None
    )
}