package com.example.BottomNavMenu.core.common

import com.example.BottomNavMenu.core.network.ApiError
import com.example.BottomNavMenu.core.network.ApiErrorHandler
import java.io.IOException
import java.net.SocketTimeoutException
import retrofit2.HttpException

/**
 * Convertit les exceptions en erreurs applicatives.
 */
object ErrorMapper {

    fun map(exception: Throwable): AppError {

        return when (exception) {

            is SocketTimeoutException ->
                AppError.Timeout

            is IOException ->
                AppError.NetworkUnavailable

            is HttpException -> mapHttpCode(
                exception.code()
            )

            else -> AppError.Unknown
        }
    }

    fun mapHttpCode(code: Int): AppError {

        return when (code) {

            400, 422 ->
                AppError.InvalidData

            401 ->
                AppError.Unauthorized

            403 ->
                AppError.Forbidden

            404 ->
                AppError.NotFound

            409 ->
                AppError.Conflict

            429 ->
                AppError.RateLimited

            in 500..599 ->
                AppError.ServerError

            else ->
                AppError.Unknown
        }
    }

    fun fromNetworkError(error: ApiError): AppError {

        return when (error) {

            ApiError.Timeout ->
                AppError.Timeout

            ApiError.NetworkUnavailable ->
                AppError.NetworkUnavailable

            ApiError.Unauthorized ->
                AppError.Unauthorized

            ApiError.Forbidden ->
                AppError.Forbidden

            ApiError.NotFound ->
                AppError.NotFound

            ApiError.ServerError ->
                AppError.ServerError

            ApiError.Unknown ->
                AppError.Unknown
        }
    }
}
