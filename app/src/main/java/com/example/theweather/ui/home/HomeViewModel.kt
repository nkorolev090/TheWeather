package com.example.theweather.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theweather.ui.home.models.ErrorUI
import com.example.theweather.ui.home.models.WeatherUI
import com.example.theweather.ui.home.models.toWeatherUI
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdata.weather.repository.RequestResult
import com.example.weatherdata.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Provider<WeatherRepository>
) : ViewModel() {


    var weatherUI = MutableLiveData<WeatherUI>().apply {
        value = WeatherUI()
    }

    var errorUI = MutableLiveData<ErrorUI>().apply {
            value = ErrorUI()
        }

    var searchText = MutableLiveData<String>().apply {
        value = "Ivanovo"
    }

    init {
        getWeather()
    }
    public fun getWeather(city: String? = searchText.value) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("VM","Request, SEARCH:"+ city)
                val notNullCity = city ?:  ""
                val response = repository.get().getAllWeather(notNullCity)

                Log.d("VM","Response: ")
                setupFields(response)
               // repository.get().saveNetResponseToCache()
            }
        }
    }

    private fun setupFields(response: RequestResult<Weather>) {
        when (response) {
            is RequestResult.Success -> {
                var notNullData = checkNotNull(response.data)
                weatherUI.postValue(notNullData.toWeatherUI())
                Log.d("VM", "Success: temp " + response.data?.temperature.toString())
            }

            is RequestResult.Error -> {
                errorUI.postValue(ErrorUI(response.message.toString(), response.code.toString()) )
                Log.e("VM", "Error")
            }

            is RequestResult.InProgress -> {
                Log.i("VM", "InProgress")
            }
        }
    }
}

