package com.example.weatherdb.models

import androidx.annotation.IntRange
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherDBO (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("temperature") val temperature: Double,
    @ColumnInfo("humidity") val humidity: Double,
    @ColumnInfo("main") val main: String,
    @ColumnInfo("pressure") val pressure: Int,
    @ColumnInfo("feelsLike") val feelsLike: Double,
    @ColumnInfo("windDeg") @IntRange(from = 0, to = 360) val windDeg: Int,
    @ColumnInfo("windSpeed") val windSpeed: Double,
)