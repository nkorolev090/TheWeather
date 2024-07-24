package com.example.weatherdata.weather.models

sealed class State{
    object None : State()
    class Loading(val weather: Weather? = null) : State()
    class Error(val weather: Weather? = null) : State()
    class Success(val weather: Weather) : State()
}