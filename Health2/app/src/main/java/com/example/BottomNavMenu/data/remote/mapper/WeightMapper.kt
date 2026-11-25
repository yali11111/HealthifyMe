package com.example.BottomNavMenu.data.remote.mapper

import com.example.BottomNavMenu.data.remote.dto.response.WeightResponse
import com.example.BottomNavMenu.domain.model.Weight

/**
 * Convertit les données de poids
 * en modèle DOMAIN.
 */

fun WeightResponse.toDomain(): Weight {

    return Weight(
        userId = userId,
        weight = weight,
        targetWeight = targetWeight ?: weight,
        unit = unit,
        recordedAt = recordedAt.orEmpty()
    )
}
