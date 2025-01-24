package com.example.weatherdata.weather

import android.util.Log
import com.example.weatherAPI.models.ResponseDTO
import com.example.weatherdata.weather.models.MainEnum
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdb.models.WeatherDBO
import java.time.LocalDateTime

internal fun WeatherDBO.toWeather() : Weather{
    return Weather(
        temperature = temperature,
        humidity = humidity,
        main = main.toMainEnum(),
        pressure = pressure,
        feelsLike = feelsLike,
        windDeg = windDeg,
        windSpeed = windSpeed,
        city = "",
        requestDateTime = requestDateTime
    )
}

private fun String.toMainEnum(): MainEnum {
    Log.d("mapper", this)
    return when(this){
        "Clear" -> {
            MainEnum.CLEAR
        }
        "Clouds" -> {
            MainEnum.CLOUDS
        }
        else -> {MainEnum.RAIN}
    }
}

internal fun ResponseDTO.toWeatherDBO() : WeatherDBO{
    return WeatherDBO(
        temperature = main.temp,
        humidity = main.humidity,
        main = weather.first().main,
        pressure = main.pressure,
        feelsLike = main.feelsLike,
        windDeg = wind.deg,
        windSpeed = wind.speed,
        city = name,
        requestDateTime = LocalDateTime.now()
        )
}

internal fun ResponseDTO.toWeather() : Weather{
    return Weather(
        temperature = main.temp,
        humidity = main.humidity,
        main = weather.first().main.toMainEnum(),
        pressure = main.pressure,
        feelsLike = main.feelsLike,
        windDeg = wind.deg,
        windSpeed = wind.speed,
        city = name,
        requestDateTime = LocalDateTime.now()
    )
}
