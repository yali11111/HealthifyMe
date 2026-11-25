package com.example.BottomNavMenu.data.remote.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Réponse API concernant les pas.
 */
data class StepsResponse(

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("steps")
    val steps: Int,

    @SerializedName("goal")
    val goal: Int? = null,

    @SerializedName("distance")
    val distance: Double? = null,

    @SerializedName("calories_burned")
    val caloriesBurned: Double? = null,

    @SerializedName("recorded_at")
    val recordedAt: String? = null
)
