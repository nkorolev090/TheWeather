package com.example.data.models

import androidx.annotation.IntRange
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WindDTO (
    @SerialName("speed")
    val speed: Double,

    @SerialName("deg")
    @IntRange(from = 0, to = 360) val deg: Int,
)