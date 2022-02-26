package com.stn.cocoatalk.presentation.initial

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.constraintlayout.utils.widget.MockView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.stn.cocoatalk.presentation.component.StandardTextField
import com.stn.cocoatalk.ui.theme.PaddingMedium
import com.stn.cocoatalk.ui.theme.PaddingSmall

@Composable
fun InitialScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(PaddingSmall))
        StandardTextField(onValueChange = )
    }
}