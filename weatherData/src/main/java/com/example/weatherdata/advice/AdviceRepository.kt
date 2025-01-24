package com.example.weatherdata.advice

import com.example.firebaseapi.recommendations.advice.AdviceAPI
import com.example.firebaseapi.recommendations.advice.models.AdviceDTO
import com.example.firebaseapi.recommendations.models.MainEnumDTO
import com.example.firebaseapi.recommendations.models.TempModeDTO
import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.StatusCodeEnum
import com.example.weathercommon.data.toRequestResult
import com.example.weathercommon.firebase.firestoreRequestFlow
import com.example.weatherdata.advice.models.Advice
import com.example.weatherdata.advice.models.toAdvice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdviceRepository @Inject constructor(
    private val adviceAPI: AdviceAPI
) {
    fun getAdvice() : Flow<RequestResult<Advice>> {

        return firestoreRequestFlow{
            adviceAPI.getAdvice()
        }.map{ requestResult ->
            requestResult.toRequestResult(
                successAction = {
                    successResult ->
                    successResult.data?.toAdvice()?.let { advice ->
                        RequestResult.Success(advice)
                    } ?: RequestResult.Error(code = StatusCodeEnum.NO_CONTENT)
                }
            )
        }
    }

    suspend fun writeAdvice(/*advice: Advice*/){
        adviceAPI.writeNewAdvice(AdviceDTO(
            tempModeDTO = TempModeDTO(
                0,
                10.0,
                0.1,
                MainEnumDTO.RAIN
            ),
            text = "Сегодня дождливо: не забудьте взять зонт"
        ))
    }
}