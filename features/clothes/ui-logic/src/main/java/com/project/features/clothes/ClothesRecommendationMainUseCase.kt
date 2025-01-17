package com.project.features.clothes

import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.map
import com.example.weatherdata.clothes.models.TempMode
import com.example.weatherdata.clothes.repository.ClothesRepository
import com.project.features.clothes.models.RecommendationStyleSectionUI
import com.project.features.clothes.models.toRecommendationStyleSectionUI
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClothesRecommendationMainUseCase @Inject constructor(
    private val clothesRepository: ClothesRepository
) {
    operator fun invoke(/*tempMode: TempMode*/): Flow<RequestResult<List<RecommendationStyleSectionUI>>> =
        clothesRepository.getAllClothes()
            .map { requestResult ->
                requestResult.map { clothesStyles ->
                    clothesStyles.map { it.toRecommendationStyleSectionUI() } }
            }
}