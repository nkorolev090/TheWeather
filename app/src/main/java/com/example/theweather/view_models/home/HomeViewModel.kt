package com.example.theweather.view_models.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherdata.weather.models.MainEnum
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

    var weatherModel = MutableLiveData<Weather>().apply {
        value = Weather(0.0, 0, MainEnum.RAIN, 0, 0.0, 0, 0.0)
    }

    var main = MutableLiveData<MainEnum>().apply {
        value = MainEnum.RAIN
    }

    var temperatureText = MutableLiveData<String>().apply {
        value = "-"
    }

    var humidityText = MutableLiveData<String>().apply {
        value = "-"
    }

    var pressureText = MutableLiveData<String>().apply {
        value = "-"
    }

    var feelsText = MutableLiveData<String>().apply {
        value = "-"
    }

    var windDegText = MutableLiveData<String>().apply {
        value = "-"
    }

    var windSpeedText = MutableLiveData<String>().apply {
        value = "-"
    }

    var errorText = MutableLiveData<String>().apply {
        value = ""
    }

    var errorCodeText = MutableLiveData<String>().apply {
        value = ""
    }

    var searchCityText = MutableLiveData<String>().apply {
        value = "Ivanovo"
    }

    var searchHintText = MutableLiveData<String>().apply {
        value = "Поиск..."
    }
    init {
        getWeather()
    }
    private fun getWeather(city: String? = searchCityText.value) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val notNullCity = city ?:  ""
                val response = repository.get().getAllWeather(notNullCity)

                Log.d("VM","Response: ")
                setupFields(response)
            }
        }
    }

    private fun setupFields(response: RequestResult<Weather>) {
        when (response) {
            is RequestResult.Success -> {
                var notNullData = checkNotNull(response.data)
                weatherModel.postValue(notNullData)
                temperatureText.postValue( if(notNullData.temperature > 0) {"+"} else {""} + notNullData.temperature.toString() + "°C")
                humidityText.postValue(notNullData.humidity.toString() + "%")
                pressureText.postValue(notNullData.pressure.toString())
                feelsText.postValue("ощущается как: " + if(notNullData.feelsLike > 0) {"+"} else {""} + notNullData.feelsLike.toString() + "°C")
                windDegText.postValue(notNullData.windDeg.toString())
                windSpeedText.postValue(notNullData.windSpeed.toString() + " м/c")
                main.postValue(notNullData.main)
                Log.d("VM", "Success: temp " + response.data?.temperature.toString())
            }

            is RequestResult.Error -> {
                errorCodeText.postValue(response.code.toString())
                errorText.postValue(response.message.toString())
                Log.e("VM", "Error")
            }

            is RequestResult.InProgress -> {
                Log.i("VM", "InProgress")
            }
        }
    }
}

