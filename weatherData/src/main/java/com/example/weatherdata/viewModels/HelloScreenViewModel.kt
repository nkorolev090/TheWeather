package com.example.weatherdata.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherdata.weather.models.State
import com.example.weatherdata.weather.models.Weather
import com.example.weatherdata.weather.repository.RequestResult
import com.example.weatherdata.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class HelloScreenViewModel @Inject constructor(
    private val repository: Provider<WeatherRepository>
) : ViewModel() {

    val state: StateFlow<State> = repository.get()
        .getAll()
        .map { it.toState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.None)

    fun forceUpdate(){
        val requestResultFlow = repository.get().fetchLatest()
    }
}

private fun  RequestResult<Weather>.toState(): State {
    return when(this){
        is RequestResult.Error -> State.Error()
        is RequestResult.InProgress -> State.Loading(data)
        is RequestResult.Success -> State.Success(data)
    }
}

