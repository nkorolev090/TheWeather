package com.project.weather.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercommon.data.RequestResult
import com.project.weather.main.model.WeatherUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WeatherMainViewModel @Inject internal constructor(
    private val weatherMainUseCase: WeatherMainUseCase
): ViewModel(){
    public val state: StateFlow<RequestResult<WeatherUI>> =
        weatherMainUseCase.invoke(location = "Ivanovo")
            .stateIn(viewModelScope, SharingStarted.Lazily, RequestResult.Loading())
}