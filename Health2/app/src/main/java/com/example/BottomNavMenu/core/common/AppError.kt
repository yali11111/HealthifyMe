package com.example.BottomNavMenu.core.common

/**
 * Catalogue centralise des erreurs BottomNavMenu.
 *
 * Ne contient aucune donnee personnelle.
 */
sealed class AppError {

    data object NetworkUnavailable : AppError()

    data object Timeout : AppError()

    data object Unauthorized : AppError()

    data object Forbidden : AppError()

    data object NotFound : AppError()

    data object ServerError : AppError()

    data object InvalidData : AppError()

    data object Validation : AppError()

    data object Database : AppError()

    data object SessionExpired : AppError()

    data object Conflict : AppError()

    data object RateLimited : AppError()

    data object Unknown : AppError()
}
