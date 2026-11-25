package com.example.BottomNavMenu.data.remote.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Réponse API concernant les calories.
 */
data class CaloriesResponse(

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("basal_metabolic_rate")
    val basalMetabolicRate: Double? = null,

    @SerializedName("daily_calories")
    val dailyCalories: Double? = null,

    @SerializedName("calories_consumed")
    val caloriesConsumed: Double? = null,

    @SerializedName("calories_burned")
    val caloriesBurned: Double? = null,

    @SerializedName("recorded_at")
    val recordedAt: String? = null
)
