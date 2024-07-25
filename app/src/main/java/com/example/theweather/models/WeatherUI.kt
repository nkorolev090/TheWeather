package com.example.theweather.models

import com.example.weatherdata.weather.models.MainEnum
import com.example.weatherdata.weather.models.Weather

class WeatherUI (
    val main :Int = 0,
    val temperatureText: String = "-",
    val humidityText: String = "-",
    val pressureText: String = "-",
    val feelsText : String = "-",
    val windDegText : String = "-",
    val windSpeedText : String = "-",
    val cityText : String = "Ivanovo",
    val searchHintText : String = "Поиск...",
)

fun Weather.toWeatherUI() : WeatherUI{
    return WeatherUI(
        temperatureText = if(temperature > 0) {"+"} else {""} + temperature.toString() + "°C",
        humidityText = "$humidity%",
        pressureText = pressure.toString(),
        feelsText = "ощущается как: " + if(feelsLike > 0) {"+"} else {""} + feelsLike.toString() + "°C",
        windSpeedText = "$windSpeed м/c",
        windDegText = windDeg.toString(),
        main = main.toResource()
    )
}

private fun MainEnum.toResource(): Int {
return 0
}