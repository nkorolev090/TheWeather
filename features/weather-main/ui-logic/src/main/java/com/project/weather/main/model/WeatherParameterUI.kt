package com.project.weather.main.model


sealed class WeatherParameterUI(val backgroundId: Int) {
    class Humidity(val largeText: String, val mediumText: String, backgroundId: Int) :
        WeatherParameterUI(backgroundId)

    class FeelsLikeTemperature(val mediumText: String, backgroundId: Int) :
        WeatherParameterUI(backgroundId)

    class WindSpeed(val largeText: String, val smallText: String, backgroundId: Int) :
        WeatherParameterUI(backgroundId)

    class Temperature(val largeText: String, backgroundId: Int) :
        WeatherParameterUI(backgroundId)
}