package com.example.weatherdata.weather.repository

import com.example.data.WeatherApi
import com.example.data.models.ResponseDTO
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdb.WeatherDatabase
import com.example.weatherdb.models.WeatherDBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

class WeatherRepository(
    private val database: WeatherDatabase,
    private val api: WeatherApi,
) {

    fun getAll(mergeStrategy: MergeStrategy<RequestResult<Weather>> = DefaultRequestResponseMergeStrategy()): Flow<RequestResult<Weather>> {

        val cachedWeather: Flow<RequestResult<Weather>> =
            getAllFromDatabase()
                .map { result ->
                    result.map{ it.toWeather() }
                }

        val remoteWeather = getAllFromServer()
            .map { result ->
                result.map{ it.toWeather() }
            }

        return cachedWeather.combine(remoteWeather, mergeStrategy::merge)
            .flatMapConcat { result ->
                if(result is RequestResult.Success) {
                    database.weatherDao.observeAll()
                        .map { it.toWeather() }
                        .map { RequestResult.Success(it) }
                }else{
                        flowOf(result)
                }
            }
    }

    private fun getAllFromServer(): Flow<RequestResult<ResponseDTO>> {
        val apiRequest = flow { emit(api.weather(city = ""))}
            .onEach { result ->
                if(result.isSuccess){
                    saveNetResponseToCache(checkNotNull(result.getOrThrow()))
                }
            }
            .map { it.toRequestResult() }

        val start = flowOf<RequestResult<ResponseDTO>>(RequestResult.InProgress())

        return merge(apiRequest, start)
    }

    private suspend fun saveNetResponseToCache(data: ResponseDTO) {
        val dbos = data
            .toWeatherDBO()
        database.weatherDao.insert(dbos)
    }

    private fun getAllFromDatabase(): Flow<RequestResult<WeatherDBO>> {
        val dbRequest =  database.weatherDao::getAll
            .asFlow()
            .map { RequestResult.Success(it) }
        val start = flowOf<RequestResult<WeatherDBO>>(RequestResult.InProgress())
        return merge(start, dbRequest)
    }
}