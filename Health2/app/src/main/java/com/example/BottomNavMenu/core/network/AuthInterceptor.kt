package com.example.BottomNavMenu.core.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Ajoute le token d'authentification.
 *
 * Le token est fourni par un composant externe.
 * Ne jamais le coder en dur.
 */
class AuthInterceptor(
    private val tokenProvider: () -> String?
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val token = tokenProvider()

        val requestBuilder = originalRequest.newBuilder()
            .header(
                NetworkConstants.HEADER_CONTENT_TYPE,
                NetworkConstants.CONTENT_TYPE_JSON
            )

        if (!token.isNullOrBlank()) {

            requestBuilder.header(
                NetworkConstants.HEADER_AUTHORIZATION,
                "Bearer $token"
            )
        }

        return chain.proceed(requestBuilder.build())
    }
}
