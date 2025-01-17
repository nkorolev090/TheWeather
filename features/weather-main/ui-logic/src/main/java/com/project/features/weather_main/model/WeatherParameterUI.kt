package com.project.features.weather_main.model


sealed class WeatherParameterUI {
    class Humidity(val largeText: String, val mediumText: String) :
        WeatherParameterUI()

    class FeelsLikeTemperature(val mediumText: String) :
        WeatherParameterUI()

    class WindSpeed(val largeText: String, val smallText: String) :
        WeatherParameterUI()

    class Temperature(val largeText: String) :
        WeatherParameterUI()
}