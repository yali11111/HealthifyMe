package com.example.health2.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Structure générique des réponses du serveur.
 */
data class ApiResponse<T>(

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("data")
    val data: T? = null
)
