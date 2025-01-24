package com.project.features.weather_main

import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.map
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdata.weather.WeatherRepository
import com.project.features.weather_main.models.WeatherUI
import com.project.features.weather_main.models.toWeatherUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class WeatherMainUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(location: String): Flow<RequestResult<WeatherUI>> =
        weatherRepository.getAllWeather(location).map { requestResult: RequestResult<Weather> ->
            requestResult.map { weather: Weather -> weather.toWeatherUI() }
        }
}