package com.stn.cocoatalk.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.stn.cocoatalk.ui.theme.YellowAccent

@Composable
fun SpanStyleText(
    text: String,
    clickableText: String,
    onclick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onclick)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            style = MaterialTheme.typography.body1,
            text = buildAnnotatedString {
                append(text)
                withStyle(SpanStyle(color = YellowAccent)) {
                    append(" ")
                    append(clickableText)
                }
            }
        )
    }
}