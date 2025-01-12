package com.project.weather.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weathercommon.data.RequestResult
import com.project.weather.main.model.WeatherUI
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
    val state by viewModel.state.collectAsState()
    val currentState = state

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
            when(currentState){
                is RequestResult.Error -> Unit
                is RequestResult.Loading -> Unit
                is RequestResult.Success -> {
                    WeatherMainContent(currentState.data)
                }
            }
        }

    }
}

@Composable
internal fun WeatherMainContent(currentState: WeatherUI, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
    ) {
        LocationTitle(
            location = currentState.cityText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 70.dp)
        )
        WeatherParameters(currentState.humidity, currentState.windSpeed, currentState.feelsLikeTemperature, currentState.temperature,
            modifier = Modifier
                .padding(horizontal = 7.dp)
                .height(IntrinsicSize.Min)
                .fillMaxWidth())
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