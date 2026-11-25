package com.example.BottomNavMenu.data.remote.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Réponse API concernant l'utilisateur.
 */
data class UserResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("age")
    val age: Int? = null,

    @SerializedName("height")
    val height: Double? = null,

    @SerializedName("weight")
    val weight: Double? = null,

    @SerializedName("created_at")
    val createdAt: String? = null
)
