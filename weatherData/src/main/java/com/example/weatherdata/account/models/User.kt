package com.example.weatherdata.account.models

import com.example.firebaseapi.recommendations.account.models.UserDTO

data class User (
    val photoUrl: String?,
    val name: String?,
    val email: String?,
    val uId: String?
)

fun UserDTO.toUser() =
    User(
        photoUrl,
        name,
        email,
        uId
    )