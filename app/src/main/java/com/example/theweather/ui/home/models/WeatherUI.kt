package com.example.theweather.ui.home.models

import com.example.theweather.R
import com.example.weatherdata.weather.models.MainEnum
import com.example.weatherdata.weather.models.Weather

class WeatherUI (
    val main :Int = R.drawable.rainy_weather,
    val temperatureText: String = "-",
    val humidityText: String = "-",
    val pressureText: String = "-",
    val feelsText : String = "-",
    val windDegText : String = "-",
    val windSpeedText : String = "-",
    val cityText : String = "...",
    val searchHintText : String = "Поиск...",
)

fun Weather.toWeatherUI() : WeatherUI {
    return WeatherUI(
        temperatureText = if(temperature > 0) {"+"} else {""} + temperature.toString() + "°C",
        humidityText = "$humidity%",
        pressureText = pressure.toString(),
        feelsText = "ощущается как: " + if(feelsLike > 0) {"+"} else {""} + feelsLike.toString() + "°C",
        windSpeedText = "$windSpeed м/c",
        windDegText = windDeg.toString(),
        cityText = city,
        main = main.toResource()
    )
}

private fun MainEnum.toResource(): Int {
return when(this){
    MainEnum.RAIN -> R.drawable.rainy_weather
    MainEnum.CLOUDS -> R.drawable.cloud_weather
    MainEnum.CLEAR -> R.drawable.cloud_weather
}
}