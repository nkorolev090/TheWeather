package com.example.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather (
    @SerialName("main")
    val main: MainEnum,
)