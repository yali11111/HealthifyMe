package com.example.BottomNavMenu.data.remote.mapper

import com.example.BottomNavMenu.data.remote.dto.response.SuggestionResponse
import com.example.BottomNavMenu.domain.model.Suggestion

/**
 * Convertit les suggestions API
 * en modèles DOMAIN.
 */

fun SuggestionResponse.toDomain(): Suggestion {

    return Suggestion(
        id = id,
        title = title,
        description = description,
        category = category,
        priority = priority ?: 0,
        createdAt = createdAt.orEmpty()
    )
}
