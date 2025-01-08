package com.project.weather.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.uikit.TheWeatherTheme
import com.project.weather.main.model.WeatherParameterUI
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
            WeatherParameters(parameters[0], parameters[1], parameters[2], parameters[3],
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth())
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


private val parameters = listOf(
    WeatherParameterUI.Humidity("20%","влажность", R.drawable.gradient1),
    WeatherParameterUI.WindSpeed("5 м/с","скорость ветра", R.drawable.gradient2),
    WeatherParameterUI.FeelsLikeTemperature("ощущается как: +18℃ ", R.drawable.gradient5),
    WeatherParameterUI.Temperature("+21℃ ", R.drawable.gradient4),
)