package com.example.theweather

import android.content.Context
import com.example.clothesdb.ClothesDatabase
import com.example.clothesdb.ClothesRoomDatabase
import com.example.data.WeatherApi
import com.example.weathercommon.AppDispatchers
import com.example.weatherdb.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient?{
        if(BuildConfig.DEBUG){
            val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            return   OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

        return null
    }
    @Provides
    @Singleton
    fun provideWeatherApi(okHttpClient: OkHttpClient?): WeatherApi{

        return WeatherApi(
            baseUrl = BuildConfig.WEATHER_API_BASE_URL,
            apiKey = BuildConfig.WEATHER_API_KEY,
            okHttpClient = okHttpClient
        )
    }
    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase{
        return WeatherDatabase(context)
    }
    @Provides
    @Singleton
    fun provideClothesDatabase(@ApplicationContext context: Context): ClothesDatabase {
        return ClothesDatabase(context)
    }
    @Provides
    @Singleton
    fun provideAppCoroutineDispatchers(): AppDispatchers{
        return AppDispatchers()
    }
}