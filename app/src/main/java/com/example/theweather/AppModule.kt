package com.example.theweather

import android.content.Context
import com.example.data.WeatherApi
import com.example.weatherdb.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi{
        return WeatherApi(
            baseUrl = BuildConfig.WEATHER_API_BASE_URL,
            apiKey = BuildConfig.WEATHER_API_KEY
        )
    }
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase{
        return WeatherDatabase(context)
    }
}