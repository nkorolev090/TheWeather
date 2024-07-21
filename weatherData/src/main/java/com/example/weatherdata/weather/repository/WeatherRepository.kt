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

    suspend fun getAllWeather(): RequestResult<Weather>{
        val response = handleApi { api.weatherResponse(city = "Ivanovo") }


        return when(response){
            is RequestResultAPI.Success->RequestResult.Success(response.data.toWeather())
            is RequestResultAPI.Error->RequestResult.Error()
            is RequestResultAPI.InProgress->RequestResult.InProgress()
            is RequestResultAPI.Exception->RequestResult.Error(error = response.throwable)
        }
    }
    fun getAll(mergeStrategy: MergeStrategy<RequestResult<Weather>> = DefaultRequestResponseMergeStrategy()): Flow<RequestResult<Weather>> {

        val cachedWeather: Flow<RequestResult<Weather>> = getAllFromDatabase()

        val remoteWeather = getAllFromServer()

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

    private fun getAllFromServer(): Flow<RequestResult<Weather>> {
        Log.d("Repo", "getAllFromServer()");

        val apiRequest = flow { emit(api.weatherResult(city = "Ivanovo"))}//перенести потом
            .onEach { result ->
                if(result.isSuccess){
                    Log.d("Repos", "Success = ${result.getOrNull()}")
                    saveNetResponseToCache(result.getOrThrow())
                }else{
                    Log.e("Repos", "Error = ${result.exceptionOrNull()}")
                }
            }
            .map { it.toRequestResult() }

        val start = flowOf<RequestResult<ResponseDTO>>(RequestResult.InProgress())

        return merge(apiRequest, start)
            .map { result ->
                result.map{ it.toWeather() }
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

    fun fetchLatest(): Flow<RequestResult<Weather>>{
        return getAllFromServer()
    }
}