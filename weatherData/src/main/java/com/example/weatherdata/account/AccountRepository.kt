package com.example.weatherdata.account

import com.example.firebaseapi.recommendations.account.AccountAPI
import com.example.firebaseapi.recommendations.account.models.UserDTO
import com.example.weathercommon.api.RequestResultAPI
import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.StatusCodeEnum
import com.example.weathercommon.data.toRequestResult
import com.example.weathercommon.firebase.firestoreRequestFlow
import com.example.weatherdata.account.models.CredModel
import com.example.weatherdata.account.models.User
import com.example.weatherdata.account.models.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountRepository @Inject constructor(private val accountAPI: AccountAPI) {

    fun getCurrentUser(): RequestResult<User> =
        accountAPI.getCurrentUser()?.let {
            RequestResult.Success(it.toUser())
        } ?: RequestResult.Error(code = StatusCodeEnum.UNAUTHORIZED)

    fun register(credModel: CredModel): Flow<RequestResult<Boolean>> =
        firestoreRequestFlow {
            accountAPI.register(credModel.email, credModel.password)
        }.map {
            it.toAccountResult()
        }

    fun signIn(credModel: CredModel): Flow<RequestResult<Boolean>> =
        firestoreRequestFlow {
            accountAPI.signIn(credModel.email, credModel.password)
        }.map {
            it.toAccountResult()
        }

    fun signOut() = accountAPI.signOut()

    private fun RequestResultAPI<UserDTO?>.toAccountResult() =
        this.toRequestResult(
            successAction = { successResult ->
                if (successResult.data != null) {
                    RequestResult.Success(true)
                } else {
                    RequestResult.Error(code = StatusCodeEnum.UNAUTHORIZED)
                }
            }
        )
}