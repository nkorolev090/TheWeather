package com.example.firebaseapi.recommendations.account.models

import com.google.firebase.auth.FirebaseUser

data class UserDTO (
    val photoUrl: String?,
    val name: String?,
    val email: String?,
    val uId: String?
)

fun FirebaseUser.toUserDTO(): UserDTO =
    UserDTO(
        photoUrl = photoUrl?.toString(),
        name = displayName,
        email = email,
        uId = uid
    )