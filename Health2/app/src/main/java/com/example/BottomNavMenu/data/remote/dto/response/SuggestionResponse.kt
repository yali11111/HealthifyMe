package com.example.BottomNavMenu.data.remote.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Réponse API concernant les suggestions.
 */
data class SuggestionResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("priority")
    val priority: Int? = null,

    @SerializedName("created_at")
    val createdAt: String? = null
)
