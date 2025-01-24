package com.project.features.recommendations.advice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercommon.data.RequestResult
import com.example.weatherdata.advice.AdviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdviceMainViewModel @Inject internal constructor(
    adviceMainUseCase: AdviceMainUseCase,
): ViewModel(){
    val state: StateFlow<RequestResult<AdviceUI>> =
        adviceMainUseCase.invoke()
            .stateIn(viewModelScope, SharingStarted.Lazily, RequestResult.Loading())
}