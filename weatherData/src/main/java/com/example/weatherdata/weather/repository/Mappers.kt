package com.example.weatherdata.weather.repository

import com.example.data.models.MainEnumDTO
import com.example.data.models.ResponseDTO
import com.example.weatherdata.weather.models.MainEnum
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdb.models.WeatherDBO

internal fun WeatherDBO.toWeather() : Weather{
    return Weather(
        temperature = temperature,
        humidity = humidity,
        main = main.toMainEnum(),
        pressure = pressure,
        feelsLike = feelsLike,
        windDeg = windDeg,
        windSpeed = windSpeed
    )
}

private fun String.toMainEnum(): MainEnum {
    return MainEnum.RAIN
}

internal fun ResponseDTO.toWeatherDBO() : WeatherDBO{
    return WeatherDBO(
        id = id,
        temperature = main.temp,
        humidity = main.humidity,
        main = weather.first().main,
        pressure = main.pressure,
        feelsLike = main.feelsLike,
        windDeg = wind.deg,
        windSpeed = wind.speed)
}

internal fun ResponseDTO.toWeather() : Weather{
    return Weather(
        temperature = main.temp,
        humidity = main.humidity,
        main = weather.first().main.toMainEnum(),
        pressure = main.pressure,
        feelsLike = main.feelsLike,
        windDeg = wind.deg,
        windSpeed = wind.speed
    )
}

private fun MainEnumDTO.toMainEnum(): MainEnum {
    return MainEnum.RAIN
}
