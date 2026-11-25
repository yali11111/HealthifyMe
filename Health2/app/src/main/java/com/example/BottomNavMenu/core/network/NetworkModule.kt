package com.example.BottomNavMenu.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Configuration centrale du réseau.
 *
 * L'injection Hilt sera réalisée en PARTIE 05.
 */
object NetworkModule {

    fun provideGson(): Gson {

        return GsonBuilder()
            .create()
    }

    fun provideOkHttpClient(
        tokenProvider: () -> String?
    ): OkHttpClient {

        return OkHttpClient.Builder()

            .connectTimeout(
                NetworkConstants.CONNECT_TIMEOUT,
                TimeUnit.SECONDS
            )

            .readTimeout(
                NetworkConstants.READ_TIMEOUT,
                TimeUnit.SECONDS
            )

            .writeTimeout(
                NetworkConstants.WRITE_TIMEOUT,
                TimeUnit.SECONDS
            )

            .callTimeout(
                NetworkConstants.CALL_TIMEOUT,
                TimeUnit.SECONDS
            )

            .addInterceptor(
                AuthInterceptor(tokenProvider)
            )

            .addInterceptor(
                NetworkInterceptor()
            )

            .build()
    }

    fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {

        return Retrofit.Builder()

            .baseUrl(NetworkConstants.BASE_URL)

            .client(client)

            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )

            .build()
    }
}
