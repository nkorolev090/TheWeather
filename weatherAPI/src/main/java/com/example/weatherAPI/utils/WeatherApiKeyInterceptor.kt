package com.example.weatherAPI.utils

import okhttp3.Interceptor
import okhttp3.Response

class WeatherApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        val request =
//            chain.request().newBuilder()
//                .url(
//                    chain.request().url.newBuilder()
//                        .addQueryParameter("X-Api-Key", apiKey)
//                        .build()
//                )
//                .build()
//        return chain.proceed(request)
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("X-Api-Key", apiKey)
                .build()
        )
    }
}