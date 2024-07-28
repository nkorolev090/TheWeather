package com.example.weatherdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherdb.models.WeatherDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    suspend fun getAll(): WeatherDBO

    @Query("SELECT * FROM weather WHERE city = :city")
    suspend fun getFromCity(city: String): List<WeatherDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherDBO: WeatherDBO)

    @Delete
    suspend fun remove(weatherDBO: WeatherDBO)

    @Query("DELETE FROM weather")
    suspend fun clean()
}