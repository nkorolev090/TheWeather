package com.example.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO (
    @SerialName("weather")
    val weather: WeatherDTO,

    @SerialName("main")
    val main: MainDTO,

    @SerialName("wind")
    val wind: WindDTO,

    @SerialName("name")
    val name: String,

    @SerialName("cod")
    val cod: Int,

    @SerialName("id")
    val id: Long,
)