package com.example.health2.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Structure des erreurs retournées par le serveur.
 */
data class ErrorResponse(

    @SerializedName("status")
    val status: Int? = null,

    @SerializedName("error")
    val error: String? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("timestamp")
    val timestamp: Long? = null
)
