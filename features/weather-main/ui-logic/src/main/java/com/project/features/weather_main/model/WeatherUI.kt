package com.project.features.weather_main.model

import com.example.weatherdata.weather.models.Weather
import java.time.LocalDateTime

class WeatherUI (
    //val main :Int = R.drawable.rainy_weather,
    val temperature: WeatherParameterUI.Temperature,
    val humidity: WeatherParameterUI.Humidity,
    val feelsLikeTemperature : WeatherParameterUI.FeelsLikeTemperature,
    val windSpeed : WeatherParameterUI.WindSpeed,
    val cityText : String = "...",
    val searchHintText : String = "Поиск...",
    val requestDateTimeText: String = "",
    val shouldShowRequestTime: Boolean = requestDateTimeText != ""
)

fun Weather.toWeatherUI() : WeatherUI {
    return WeatherUI(
        temperature = WeatherParameterUI.Temperature(
            largeText = if(temperature > 0) {"+"} else {""} + temperature.toString() + "°C"
        ),
        humidity = WeatherParameterUI.Humidity(
            largeText = "$humidity%",
            mediumText = "влажность"
        ),
        feelsLikeTemperature = WeatherParameterUI.FeelsLikeTemperature(
            mediumText = "ощущается как: " + if(feelsLike > 0) {"+"} else {""} + feelsLike.toString() + "°C"
        ),
        windSpeed = WeatherParameterUI.WindSpeed(
            largeText = "$windSpeed м/c",
            smallText = "скорость ветра"
        ),
        cityText = city,
        //main = main.toResource(),
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

//private fun MainEnum.toResource(): Int {
//return when(this){
//    MainEnum.RAIN -> R.drawable.rainy_weather
//    MainEnum.CLOUDS -> R.drawable.cloud_weather
//    MainEnum.CLEAR -> R.drawable.clear_weather
//}
//}