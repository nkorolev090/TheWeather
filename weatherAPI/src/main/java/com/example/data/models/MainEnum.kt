package com.example.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MainEnum{
    @SerialName("Clouds")
    CLOUDS,

    @SerialName("Clear")
    CLEAR,

    @SerialName("Rain")
    RAIN,
}