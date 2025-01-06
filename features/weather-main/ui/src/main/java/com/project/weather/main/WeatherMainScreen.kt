package com.project.weather.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .paint(
                painterResource(R.drawable.rainy_bg),
                contentScale = ContentScale.Fit
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item { TopBar() }
        item {
            LocationTitle(
                location = "Иваново",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 70.dp)
            )
        }
        item {
            WeatherParameters(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
internal fun LocationTitle(location: String, modifier: Modifier = Modifier) {
    Text(
        text = location,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

