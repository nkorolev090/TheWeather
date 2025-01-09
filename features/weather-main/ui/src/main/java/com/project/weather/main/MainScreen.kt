package com.project.weather.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.weather.main.ui.R

@Composable
fun WeatherMainScreen(modifier: Modifier = Modifier) {
    WeatherMainScreen(viewModel = viewModel(), modifier = modifier)
}

@Composable
internal fun WeatherMainScreen(
    viewModel: WeatherMainViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.back),
                    contentScale = ContentScale.FillBounds
                )
                .padding(
                    PaddingValues(
                        top = 0.dp,
                        start = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (i in 0..20) {
                item {
                    Greeting(
                        name = "TheWeather",
                        modifier = Modifier.padding(vertical = 40.dp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        ) {
            Text(
                text = "App Bar",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 0.dp, bottom = 10.dp),
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        color = Color.White,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
fun Preview() {
    MaterialTheme {
        WeatherMainScreen()
    }
}