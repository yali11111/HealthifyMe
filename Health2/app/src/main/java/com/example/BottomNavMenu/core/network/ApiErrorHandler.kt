package com.example.BottomNavMenu.core.network

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

sealed class ApiError {

    data object Unauthorized : ApiError()

    data object Forbidden : ApiError()

    data object NotFound : ApiError()

    data object ServerError : ApiError()

    data object NetworkUnavailable : ApiError()

    data object Timeout : ApiError()

    data object Unknown : ApiError()
}

object ApiErrorHandler {

    fun handle(exception: Throwable): ApiError {

        return when (exception) {

            is SocketTimeoutException ->
                ApiError.Timeout

            is IOException ->
                ApiError.NetworkUnavailable

            is HttpException -> {

                when (exception.code()) {

                    401 -> ApiError.Unauthorized

                    403 -> ApiError.Forbidden

                    404 -> ApiError.NotFound

                    in 500..599 -> ApiError.ServerError

                    else -> ApiError.Unknown
                }
            }

            else -> ApiError.Unknown
        }
    }
}
