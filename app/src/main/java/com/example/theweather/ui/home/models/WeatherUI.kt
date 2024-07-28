package com.example.theweather.ui.home.models

import com.example.theweather.R
import com.example.weatherdata.weather.models.MainEnum
import com.example.weatherdata.weather.models.Weather
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

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
    val requestDateTimeText: String = "",
    val shouldShowRequestTime: Boolean = requestDateTimeText != ""
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
        main = main.toResource(),
        requestDateTimeText = requestDateTime.toRequestDateTimeText()
    )
}

private fun LocalDateTime.toRequestDateTimeText(): String {
    val localDateTime: Long = this.hour.toLong() * 3600 + this.minute.toLong() * 60 + this.second.toLong()
    val nowTime = LocalDateTime.now()
    val now: Long = nowTime.hour.toLong() * 3600 + nowTime.minute.toLong() * 60 + nowTime.second.toLong()

    return if (now - localDateTime > 60){
        "Данные актуальны на ${this.hour}:" + this.minute.toStringMinutes() + " ${this.dayOfMonth} ${this.month}"
    }else{
        ""
    }
}

private fun Int.toStringMinutes(): String{
 return if(this < 10){
     "0${this}"
 }
    else{
     "$this"
 }
}

private fun MainEnum.toResource(): Int {
return when(this){
    MainEnum.RAIN -> R.drawable.rainy_weather
    MainEnum.CLOUDS -> R.drawable.cloud_weather
    MainEnum.CLEAR -> R.drawable.cloud_weather
}
}