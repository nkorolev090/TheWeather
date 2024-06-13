package com.example.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response (
    @SerialName("weather")
    val weather: Weather,

    @SerialName("main")
    val main: Main,

    @SerialName("wind")
    val wind: Wind,

    @SerialName("name")
    val name: String,

    @SerialName("cod")
    val cod: Int,
)