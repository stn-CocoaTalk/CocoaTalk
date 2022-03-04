package com.stn.cocoatalk.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LabelTextField(
    text: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            label = { Text( text = label ) },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}