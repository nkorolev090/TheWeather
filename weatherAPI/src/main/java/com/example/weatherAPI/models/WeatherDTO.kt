package com.example.weatherAPI.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO (
    @SerialName("main")
    val main: String,
)