package com.example.weatherdata.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.weatherdata.weather.models.MainEnum
import com.example.weatherdata.weather.models.State
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdata.weather.repository.RequestResult
import com.example.weatherdata.weather.repository.WeatherRepository
import com.example.weatherdata.weather.repository.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class HelloScreenViewModel @Inject constructor(
    private val repository: Provider<WeatherRepository>
) : ViewModel() {

//    val state: StateFlow<State> = repository.get()
//        .getAll()
//        .map { it.toState() }
//        .stateIn(viewModelScope, SharingStarted.Lazily, State.None)
//
//    fun forceUpdate(){
//        val requestResultFlow = repository.get().fetchLatest()
//    }

    //private var weatherResult = MutableLiveData<RequestResult<Weather>>()

//    private var _temperatureText = MutableLiveData<Weather>().apply {
//        value = Weather(0.0, 0, MainEnum.RAIN, 0, 0.0, 0, 0.0)
//    }
//    var temperatureText: LiveData<Weather> = _temperatureText
//
//    fun getWeather() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val response = repository.get().getAllWeather()
//                //weatherResult.postValue(response)
//                Log.d("VM","Response: ")
//                when (response) {
//                    is RequestResult.Success ->{
//                        _temperatureText.postValue(checkNotNull(response.data))
//                        Log.d("VM","Success: temp " + response.data?.temperature.toString())
//                    }
//                    is RequestResult.Error ->{
//                        Log.e("VM","Error")
//                    }
//                    is RequestResult.InProgress ->{
//                        Log.i("VM","InProgress")
//                    }
//                }
//                //setAllWeather(weatherResult)
//            }
//        }
//    }

//    private fun setAllWeather(mutableLiveData: MutableLiveData<RequestResult<Weather>>){
//       // allWeather = mutableLiveData.map { requestResult ->  checkNotNull(requestResult.data)}
//        temperatureText = mutableLiveData.map { requestResult ->  checkNotNull(requestResult.data)
//}
}

private fun  RequestResult<Weather>.toState(): State {
    return when(this){
        is RequestResult.Error -> State.Error()
        is RequestResult.InProgress -> State.Loading(data)
        is RequestResult.Success -> State.Success(data)
    }
}

