package com.project.weather.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.weather.main.ui.R

@Preview
@Composable
fun HelloMainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.hello_bg),
                contentScale = ContentScale.FillBounds
            )
            .padding(PaddingValues(0.dp))
    ) {
        Text(
            text = "Hello, World!",
           textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 30.sp
        )
    }
}
