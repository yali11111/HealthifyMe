package com.example.BottomNavMenu.core.di

import com.example.BottomNavMenu.core.network.AuthInterceptor
import com.example.BottomNavMenu.core.network.NetworkConstants
import com.example.BottomNavMenu.core.network.NetworkInterceptor
import com.example.BottomNavMenu.core.security.SecurePreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Injection des composants réseau.
 *
 * Le token est lu depuis le stockage securise.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        securePreferences: SecurePreferences
    ): AuthInterceptor {

        return AuthInterceptor {
            securePreferences.getString("access_token")
        }
    }

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): NetworkInterceptor {
        return NetworkInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()

            .connectTimeout(30, TimeUnit.SECONDS)

            .readTimeout(30, TimeUnit.SECONDS)

            .writeTimeout(30, TimeUnit.SECONDS)

            .callTimeout(60, TimeUnit.SECONDS)

            .addInterceptor(authInterceptor)

            .addInterceptor(networkInterceptor)

            .build()
    }

    @Provides
    @Singleton
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
