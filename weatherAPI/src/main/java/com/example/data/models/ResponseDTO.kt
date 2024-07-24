package com.example.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ResponseDTO (

    @SerialName("weather")
    val weather: Array<WeatherDTO>,

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