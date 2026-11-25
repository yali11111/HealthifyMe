package com.example.BottomNavMenu.data.remote.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Représente une erreur provenant du serveur.
 */
data class ErrorResponse(

    @SerializedName("code")
    val code: Int? = null,

    @SerializedName("error")
    val error: String? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("details")
    val details: String? = null
)
