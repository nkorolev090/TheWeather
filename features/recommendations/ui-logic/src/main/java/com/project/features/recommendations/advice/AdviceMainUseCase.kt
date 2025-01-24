package com.project.features.recommendations.advice

import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.map
import com.example.weatherdata.advice.AdviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AdviceMainUseCase @Inject constructor(
    private val adviceRepository: AdviceRepository
) {
    operator fun invoke(): Flow<RequestResult<AdviceUI>> =
        adviceRepository.getAdvice().map {
            it.map { advice ->
                advice.toAdviceUI()
            }
        }
}