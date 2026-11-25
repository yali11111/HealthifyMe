package com.example.BottomNavMenu.data.remote.mapper

import com.example.BottomNavMenu.data.remote.dto.response.CaloriesResponse
import com.example.BottomNavMenu.domain.model.Calories

/**
 * Convertit les données caloriques
 * en modèle DOMAIN.
 */

fun CaloriesResponse.toDomain(): Calories {

    return Calories(
        userId = userId,
        basalMetabolicRate = basalMetabolicRate ?: 0.0,
        dailyCalories = dailyCalories ?: 0.0,
        caloriesConsumed = caloriesConsumed ?: 0.0,
        caloriesBurned = caloriesBurned ?: 0.0,
        recordedAt = recordedAt.orEmpty()
    )
}
