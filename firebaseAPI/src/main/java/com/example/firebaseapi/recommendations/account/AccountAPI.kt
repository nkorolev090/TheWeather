package com.example.firebaseapi.recommendations.account

import com.example.firebaseapi.recommendations.account.models.UserDTO
import com.example.firebaseapi.recommendations.account.models.toUserDTO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountAPI @Inject constructor(
    private val auth: FirebaseAuth
) {
    fun getCurrentUser(): UserDTO? =
        auth.currentUser?.toUserDTO()

    suspend fun signIn(email: String, password: String): UserDTO? =
        auth
            .signInWithEmailAndPassword(email, password)
            .await()
            .user
            ?.toUserDTO()

    fun signOut() =
        auth.signOut()

    suspend fun register(email: String, password: String) : UserDTO? =
        auth
            .createUserWithEmailAndPassword(email, password)
            .await()
            .user
            ?.toUserDTO()
}