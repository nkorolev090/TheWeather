package com.project.features.weather_main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercommon.data.RequestResult
import com.example.weatherdata.clothes.repository.ClothesRepository
import com.project.features.weather_main.models.WeatherUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherMainViewModel @Inject internal constructor(
    private val weatherMainUseCase: WeatherMainUseCase,
    private val clothesRepository: ClothesRepository
): ViewModel(){
    val state: StateFlow<RequestResult<WeatherUI>> =
        weatherMainUseCase.invoke(location = "Ivanovo")
            .stateIn(viewModelScope, SharingStarted.Lazily, RequestResult.Loading())

    init {
        viewModelScope.launch {
            clothesRepository.testFirebase()
        }
    }
}