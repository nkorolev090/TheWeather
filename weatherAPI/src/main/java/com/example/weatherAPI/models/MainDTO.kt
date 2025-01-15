package com.example.weatherAPI.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainDTO (
    @SerialName("temp")
    val temp: Double,

    @SerialName("feels_like")
    val feelsLike: Double,

    @SerialName("pressure")
    val pressure: Int,

    @SerialName("humidity")
    val humidity: Int,
)