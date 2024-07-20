package com.example.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO (
    @SerialName("main")
    val main: MainEnumDTO,
)