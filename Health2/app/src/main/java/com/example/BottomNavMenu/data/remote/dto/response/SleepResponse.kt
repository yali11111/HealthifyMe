package com.example.BottomNavMenu.data.remote.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Réponse API concernant le sommeil.
 *
 * Durées exprimées en minutes.
 */
data class SleepResponse(

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("total_duration")
    val totalDuration: Int,

    @SerializedName("deep_sleep")
    val deepSleep: Int = 0,

    @SerializedName("light_sleep")
    val lightSleep: Int = 0,

    @SerializedName("rem_sleep")
    val remSleep: Int = 0,

    @SerializedName("awake_duration")
    val awakeDuration: Int = 0,

    @SerializedName("sleep_efficiency")
    val sleepEfficiency: Double? = null,

    @SerializedName("recorded_at")
    val recordedAt: String? = null
)
