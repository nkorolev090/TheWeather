package com.example.weatherdata.viewModels

import androidx.lifecycle.ViewModel
import com.example.weatherdata.weather.models.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class HelloScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(State.None)
    val state: StateFlow<State>
        get() = _state.asStateFlow()
}

sealed class State{
    object None : State()
    class Loading(val weather: Weather) : State()
    class Error : State()
    class Success(val weather: Weather) : State()
}