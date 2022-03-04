package com.stn.cocoatalk.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stn.cocoatalk.ui.theme.YellowAccent

@Composable
fun AccentText(
    text: String?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(65.dp)
            .background(
                color = YellowAccent,
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text ?: "Continue",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}