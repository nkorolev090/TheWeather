package com.example.weathercommon.data

import com.example.weathercommon.api.RequestResultAPI


sealed class RequestResult<out E: Any>(open val data: E? = null){
    class Loading<E : Any>() : RequestResult<E>()
    class Success<E : Any>(override val data: E) : RequestResult<E>(data)
    class Error<E: Any>(data: E? = null, val code: Any? = null, val message: Any? = null, val error: Throwable? = null) : RequestResult<E>(data)
}
fun <I: Any, O:Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when(this){
        is RequestResult.Success -> RequestResult.Success(mapper(data))
        is RequestResult.Error -> RequestResult.Error(code = code, message = message)
        is RequestResult.Loading -> RequestResult.Loading()
    }
}

fun <I : Any?, O : Any> RequestResultAPI<I>.toRequestResult(
    successAction: (RequestResultAPI.Success<I>) -> RequestResult<O>,
    errorAction: (RequestResultAPI.Error) -> RequestResult<O> = {
        RequestResult.Error(message = it.message, code = it.code)
    },
    exceptionAction: (RequestResultAPI.Exception) -> RequestResult<O> = {
        RequestResult.Error(error = it.throwable)
    },
    loadingAction: (RequestResultAPI.Loading) -> RequestResult<O> = {
        RequestResult.Loading()
    }
): RequestResult<O> {
    return when (this) {
        is RequestResultAPI.Error -> errorAction(this)
        is RequestResultAPI.Exception -> exceptionAction(this)
        is RequestResultAPI.Loading -> loadingAction(this)
        is RequestResultAPI.Success -> successAction(this)
    }
}

//internal fun <T : Any> Result<T>.toRequestResult(): RequestResult<T> {
//    return when{
//        isSuccess -> RequestResult.Success(getOrThrow())
//        isFailure -> RequestResult.Error()
//        else -> error("Impossible branch")
//    }
//}