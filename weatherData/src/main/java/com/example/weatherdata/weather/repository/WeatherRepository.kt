package com.example.weatherdata.weather.repository

import android.util.Log
import com.example.data.RequestResultAPI
import com.example.data.WeatherApi
import com.example.data.handleApi
import com.example.data.models.ResponseDTO
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdb.WeatherDatabase
import com.example.weatherdb.models.WeatherDBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val database: WeatherDatabase,
    private val api: WeatherApi,
) {

    suspend fun getAllWeather(city: String): RequestResult<Weather>{
        val response = handleApi { api.weatherResponse(city = city) }


        return when(response){
            is RequestResultAPI.Success->RequestResult.Success(response.data.toWeather())
            is RequestResultAPI.InProgress->RequestResult.InProgress()
            is RequestResultAPI.Error->RequestResult.Error(code = response.code, message = response.message)
            is RequestResultAPI.Exception->RequestResult.Error(error = response.throwable)
        }
    }

    private suspend fun saveNetResponseToCache(data: ResponseDTO) {
        val dbos = data
            .toWeatherDBO()
        database.weatherDao.insert(dbos)
    }

    private fun getAllFromDatabase(): Flow<RequestResult<Weather>> {
        val dbRequest =  database.weatherDao::getAll
            .asFlow()
            .map { RequestResult.Success(it) }
            .catch {
                RequestResult.Error<WeatherDBO>(error = it)
                Log.e("Repos", "Error from database= $it")
            }
        val start = flowOf<RequestResult<WeatherDBO>>(RequestResult.InProgress())
        return merge(start, dbRequest)
            .map { result ->
                result.map{ it.toWeather() }
            }
    }

}