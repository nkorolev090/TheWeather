package com.example.weathercommon.firebase

import com.example.weathercommon.api.RequestResultAPI
import com.example.weathercommon.data.StatusCodeEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull

fun <T> firestoreRequestFlow(call: suspend () -> T): Flow<RequestResultAPI<T>> = flow {
    emit(RequestResultAPI.Loading)

    withTimeoutOrNull(20000L) {
        try {
            val response = call()
            if (response != null) {
                emit(RequestResultAPI.Success(response))
            } else {
                emit(RequestResultAPI.Error(code = StatusCodeEnum.NO_CONTENT))
            }
        } catch (e: Exception) {
            emit(RequestResultAPI.Exception(e))
        }
    } ?: emit(RequestResultAPI.Error(code = StatusCodeEnum.CONNECTION_TIMED_OUT))
}.flowOn(Dispatchers.IO)