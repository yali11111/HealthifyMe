package com.example.BottomNavMenu.data.remote.mapper

import com.example.BottomNavMenu.data.remote.dto.response.SleepResponse
import com.example.BottomNavMenu.domain.model.Sleep

/**
 * Convertit les données de sommeil
 * en modèle DOMAIN.
 *
 * Les durées sont exprimées en minutes.
 */

fun SleepResponse.toDomain(): Sleep {

    return Sleep(
        userId = userId,
        totalDuration = totalDuration,
        deepSleep = deepSleep,
        lightSleep = lightSleep,
        remSleep = remSleep,
        awakeDuration = awakeDuration,
        sleepEfficiency = sleepEfficiency ?: 0.0,
        recordedAt = recordedAt.orEmpty()
    )
}
