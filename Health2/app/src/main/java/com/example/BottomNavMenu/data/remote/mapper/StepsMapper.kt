package com.example.BottomNavMenu.data.remote.mapper

import com.example.BottomNavMenu.data.remote.dto.response.StepsResponse
import com.example.BottomNavMenu.domain.model.Steps

/**
 * Convertit les statistiques de pas
 * en modèle DOMAIN.
 */

fun StepsResponse.toDomain(): Steps {

    return Steps(
        userId = userId,
        steps = steps,
        goal = goal ?: 0,
        distance = distance ?: 0.0,
        caloriesBurned = caloriesBurned ?: 0.0,
        recordedAt = recordedAt.orEmpty()
    )
}
