package com.stn.cocoatalk.presentation.chatlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.ui.theme.*

@Composable
fun ChatListItem(
    message: Message,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                color = DarkBlack,
                shape = RoundedCornerShape(PaddingSmall)
            )
            .border(
                BorderStroke(1.dp, TextWhite),
                shape = RoundedCornerShape(PaddingSmall)
            )
            .height(85.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = PaddingMedium, bottom = PaddingSmall, top = PaddingSmall, end = PaddingMedium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = message.username,
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = message.timestamp,
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(PaddingSmall))
            Text(
                text = message.text,
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }
    }
}