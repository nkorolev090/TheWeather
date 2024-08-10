package com.example.weatherdata.clothes.models

import com.example.weatherdata.weather.models.MainEnum

data class TempMode(
    val id: Long,
    val low: Double,
    val high: Double,
    val main: MainEnum,
)