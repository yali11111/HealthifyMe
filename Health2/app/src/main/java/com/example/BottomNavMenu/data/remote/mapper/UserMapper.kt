package com.example.BottomNavMenu.data.remote.mapper

import com.example.BottomNavMenu.data.remote.dto.response.UserResponse
import com.example.BottomNavMenu.domain.model.User

/**
 * Convertit les réponses utilisateur
 * en modèles DOMAIN.
 */

fun UserResponse.toDomain(): User {

    return User(
        id = id,
        name = name.orEmpty(),
        email = email.orEmpty(),
        age = age ?: 0,
        height = height ?: 0.0,
        weight = weight ?: 0.0
    )
}
