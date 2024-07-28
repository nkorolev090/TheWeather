package com.example.weatherdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherdb.dao.WeatherDao
import com.example.weatherdb.models.WeatherDBO

class WeatherDatabase internal constructor(private val database: WeatherRoomDatabase){
    val weatherDao: WeatherDao
        get() = database.weatherDao()
}
@Database(entities = [WeatherDBO::class], version = 2)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class WeatherRoomDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}
fun WeatherDatabase(applicationContext: Context): WeatherDatabase {
    val weatherRoomDatabase =
        Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        WeatherRoomDatabase::class.java,
        "weather"
    ).build()
    return WeatherDatabase(weatherRoomDatabase)
}