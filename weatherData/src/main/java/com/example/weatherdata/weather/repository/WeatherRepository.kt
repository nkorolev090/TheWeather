package com.example.weatherdata.weather.repository

import com.example.data.WeatherApi
import com.example.data.models.ResponseDTO
import com.example.weathercommon.api.apiRequestFlow
import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.StatusCodeEnum
import com.example.weathercommon.data.toRequestResult
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdb.WeatherDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val database: WeatherDatabase,
    private val api: WeatherApi,
) {

    fun getAllWeather(city: String): Flow<RequestResult<Weather>> {
         return apiRequestFlow {
             api.weatherResponse(city = city)
         }.map { response -> response.toRequestResult(
             successAction = {successResponse ->
                 if(successResponse.data.weather.isNotEmpty()){
                     RequestResult.Success(successResponse.data.toWeather())
                 }else{
                     RequestResult.Error(code = StatusCodeEnum.NO_CONTENT)
                 }
             }
         ) }

//        return when(response){
//            is RequestResultAPI.Success->{
//                saveNetResponseToCache(response.data)
//                RequestResult.Success(response.data.toWeather())}
//            is RequestResultAPI.InProgress->RequestResult.InProgress()
//            is RequestResultAPI.Error->{
//                getFromDatabase(city)
//                //RequestResult.Error(code = response.code, message = response.message)
//            }
//            is RequestResultAPI.Exception-> {
//                getFromDatabase(city)
////                RequestResult.Error(error = response.throwable)
//            }
//        }
    }

    private suspend fun saveNetResponseToCache(data: ResponseDTO) {
        val dbos = data
            .toWeatherDBO()
        database.weatherDao.insert(dbos)
    }

//    suspend fun saveNetResponseToCache() {
//        val data = WeatherDBO(
//            city = "Ivanovo",
//            temperature = 20.2,
//            humidity = 88,
//            main = "rain",
//            pressure = 79,
//            feelsLike = 21.1,
//            windDeg = 20,
//            windSpeed = 3.3,
//            requestDateTime = LocalDateTime.now()
//        )
//        database.weatherDao.insert(data)
//    }
//    private fun getAllFromDatabase(): Flow<RequestResult<Weather>> {
//        val dbRequest =  database.weatherDao::getAll
//            .asFlow()
//            .map { RequestResult.Success(it) }
//            .catch {
//                RequestResult.Error<WeatherDBO>(error = it)
//                Log.e("Repos", "Error from database= $it")
//            }
//        val start = flowOf<RequestResult<WeatherDBO>>(RequestResult.InProgress())
//        return merge(start, dbRequest)
//            .map { result ->
//                result.map{ it.toWeather() }
//            }
//    }

    private suspend fun getFromDatabase(city: String): RequestResult<Weather>{
        var data = database.weatherDao.getFromCity(city).first().toWeather()//try catch
        return RequestResult.Success(data)
    }
}