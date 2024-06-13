package com.example.theweather.models

sealed class State {
    object None : State()
    class Loading : State()
    class  Error : State()
    class Success(val response: Response) : State()
}