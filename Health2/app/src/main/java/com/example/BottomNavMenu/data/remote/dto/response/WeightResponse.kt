package com.example.BottomNavMenu.data.remote.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Réponse API concernant le poids.
 */
data class WeightResponse(

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("weight")
    val weight: Double,

    @SerializedName("target_weight")
    val targetWeight: Double? = null,

    @SerializedName("unit")
    val unit: String = "kg",

    @SerializedName("recorded_at")
    val recordedAt: String? = null
)
