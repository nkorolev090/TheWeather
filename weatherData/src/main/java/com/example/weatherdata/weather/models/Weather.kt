package com.example.weatherdata.weather.models

import androidx.annotation.IntRange

data class Weather (
    val temperature: Double,
    val humidity: Int,
    val main: MainEnum,
    val pressure: Int,
    val feelsLike: Double,
    @IntRange(from = 0, to = 360) val windDeg: Int,
    val windSpeed: Double,
    val city: String,
)