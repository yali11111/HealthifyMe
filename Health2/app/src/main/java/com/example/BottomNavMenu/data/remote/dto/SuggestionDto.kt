package com.example.health2.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO représentant une suggestion de santé.
 */
data class SuggestionDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("priority")
    val priority: String? = null,

    @SerializedName("createdAt")
    val createdAt: Long? = null
)
