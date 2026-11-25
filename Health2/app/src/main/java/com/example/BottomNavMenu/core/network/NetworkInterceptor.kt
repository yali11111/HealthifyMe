package com.example.BottomNavMenu.core.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor réseau.
 *
 * Aucune donnée personnelle ou médicale
 * n'est journalisée.
 */
class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        return chain.proceed(
            request.newBuilder()
                .header("Accept", "application/json")
                .build()
        )
    }
}
