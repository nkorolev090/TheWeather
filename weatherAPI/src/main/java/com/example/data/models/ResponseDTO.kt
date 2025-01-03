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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResponseDTO

        if (!weather.contentEquals(other.weather)) return false
        if (main != other.main) return false
        if (wind != other.wind) return false
        if (name != other.name) return false
        if (cod != other.cod) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = weather.contentHashCode()
        result = 31 * result + main.hashCode()
        result = 31 * result + wind.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + cod
        result = 31 * result + id.hashCode()
        return result
    }
}