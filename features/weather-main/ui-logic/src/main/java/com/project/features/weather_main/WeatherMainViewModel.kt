package com.project.features.weather_main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercommon.data.RequestResult
import com.example.weatherdata.advice.AdviceRepository
import com.example.weatherdata.clothes.ClothesRepository
import com.project.features.weather_main.models.WeatherUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherMainViewModel @Inject internal constructor(
    weatherMainUseCase: WeatherMainUseCase,
    private val adviceRepository: AdviceRepository
): ViewModel(){
    val state: StateFlow<RequestResult<WeatherUI>> =
        weatherMainUseCase.invoke(location = "Ivanovo")
            .stateIn(viewModelScope, SharingStarted.Lazily, RequestResult.Loading())

    init {
        viewModelScope.launch {
            with(Dispatchers.Main) {
                adviceRepository.writeAdvice()
            }
        }
    }
}