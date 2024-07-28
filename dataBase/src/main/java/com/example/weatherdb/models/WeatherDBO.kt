package com.example.weatherdb.models

import androidx.annotation.IntRange
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.weatherdb.LocalDateTimeConverter
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "weather")
data class WeatherDBO (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("city")val city: String,
    @ColumnInfo("temperature") val temperature: Double,
    @ColumnInfo("humidity") val humidity: Int,
    @ColumnInfo("main") val main: String,
    @ColumnInfo("pressure") val pressure: Int,
    @ColumnInfo("feelsLike") val feelsLike: Double,
    @ColumnInfo("windDeg") @IntRange(from = 0, to = 360) val windDeg: Int,
    @ColumnInfo("windSpeed") val windSpeed: Double,
    @ColumnInfo("dateTime") val requestDateTime: LocalDateTime,
)