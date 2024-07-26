package com.example.weatherdata.clothes.models

import com.example.clothesdb.models.enums.MainEnumDBO

data class TempMode(
    val id: Long,
    val low: Double,
    val high: Double,
    val main: MainEnumDBO,
)