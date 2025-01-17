package com.project.features.clothes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercommon.data.RequestResult
import com.project.features.clothes.models.RecommendationStyleSectionUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ClothesRecommendationMainViewModel @Inject internal constructor(
   clothesRecommendationMainUseCase: ClothesRecommendationMainUseCase
) : ViewModel() {
    val state: StateFlow<RequestResult<List<RecommendationStyleSectionUI>>> =
        clothesRecommendationMainUseCase.invoke()
            .stateIn(viewModelScope, SharingStarted.Lazily, RequestResult.Loading())
}