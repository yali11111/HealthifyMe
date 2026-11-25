package com.example.BottomNavMenu.data.remote.error

/**
 * Exception representant une erreur HTTP.
 */
class ApiException(
    val statusCode: Int,
    message: String? = null
) : Exception(message)
