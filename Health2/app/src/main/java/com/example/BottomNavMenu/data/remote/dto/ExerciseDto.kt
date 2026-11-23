package com.example.health2.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO représentant un exercice physique.
 */
data class ExerciseDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("duration")
    val duration: Int? = null,

    @SerializedName("calories")
    val calories: Float? = null,

    @SerializedName("difficulty")
    val difficulty: String? = null,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("videoUrl")
    val videoUrl: String? = null
)
