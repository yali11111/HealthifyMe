package com.example.BottomNavMenu.core.common

import kotlinx.coroutines.CancellationException
import java.io.IOException
import java.net.SocketTimeoutException
import retrofit2.HttpException

/**
 * Gestion centralisee des erreurs.
 */
object ErrorHandler {

    fun handle(exception: Throwable): AppError {

        if (exception is CancellationException) {
            throw exception
        }

        return ErrorMapper.map(exception)
    }

    fun <T> execute(
        operation: () -> T
    ): AppResult<T> {

        return try {

            AppResult.Success(operation())

        } catch (exception: CancellationException) {

            throw exception

        } catch (exception: Exception) {

            AppResult.Error(
                handle(exception)
            )
        }
    }

    suspend fun <T> executeSuspend(
        operation: suspend () -> T
    ): AppResult<T> {

        return try {

            AppResult.Success(operation())

        } catch (exception: CancellationException) {

            throw exception

        } catch (exception: Exception) {

            AppResult.Error(
                handle(exception)
            )
        }
    }
}
