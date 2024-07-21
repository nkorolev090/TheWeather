@file:Suppress("unused")

package com.example.data

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.example.data.models.ResponseDTO
import com.example.data.utils.WeatherApiKeyInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun weatherResponse(
        @Query("units") units: String? = "metric",
        @Query("q") city: String,
    ): Response<ResponseDTO>

    @GET("weather")
    suspend fun weatherResult(
        @Query("units") units: String? = "metric",
        @Query("q") city: String,
    ): Result<ResponseDTO>
}
suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): RequestResultAPI<T> {
    return try {
        val response= execute()
        val body = response.body()
        if (response.isSuccessful) {
            RequestResultAPI.Success(data = checkNotNull(body))
        } else {
            RequestResultAPI.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        RequestResultAPI.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        RequestResultAPI.Exception(throwable = e)
    }
}

sealed class RequestResultAPI<out E: Any>(open val data: E? = null){

    class InProgress<E: Any>(data: E? = null) : RequestResultAPI<E>(data)
    class Success<E : Any>(override val data: E) : RequestResultAPI<E>(data)
    class Error<E: Any>(val code: Any? = null,val message: Any? = null) : RequestResultAPI<E>()
    class Exception<E: Any>(val throwable: Throwable? = null) : RequestResultAPI<E>()
}

fun WeatherApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
): WeatherApi{
    return retrofit(baseUrl, apiKey, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
    ): Retrofit {
    val jsonConverterFactory = Json{
        isLenient = true
        ignoreUnknownKeys = true
    }.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(WeatherApiKeyInterceptor(apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}
