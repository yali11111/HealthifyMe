package com.example.health2.di

import com.example.health2.BuildConfig import com.example.health2.data.remote.api.ApiService import com.google.gson.Gson import com.google.gson.GsonBuilder import dagger.Module import dagger.Provides import dagger.hilt.InstallIn import dagger.hilt.components.SingletonComponent import okhttp3.OkHttpClient import okhttp3.logging.HttpLoggingInterceptor import retrofit2.Retrofit import retrofit2.converter.gson.GsonConverterFactory import java.util.concurrent.TimeUnit import javax.inject.Singleton

/**

Module de configuration des dépendances réseau
de l'application Health2.
Ce module permet de fournir automatiquement
les composants nécessaires à Retrofit. */ @Module @InstallIn(SingletonComponent::class) object AppModule {

/**

Adresse de base du serveur Health2.
À remplacer par l'adresse réelle de ton API. */ private const val BASE_URL = "https://api.example.com/"

// ----------------------------------------------- // 1. CONFIGURATION GSON // -----------------------------------------------

/**

Fournit un objet Gson.
Gson permet de convertir les objets Kotlin
en JSON et les réponses JSON en objets Kotlin. */ @Provides @Singleton fun provideGson(): Gson {

return GsonBuilder() .create() }

// ----------------------------------------------- // 2. CONFIGURATION DES LOGS HTTP // -----------------------------------------------

/**

Fournit un intercepteur HTTP.
Il permet d'observer les requêtes HTTP
pendant le développement. */ @Provides @Singleton fun provideLoggingInterceptor(): HttpLoggingInterceptor {

return HttpLoggingInterceptor().apply {

 level = if (BuildConfig.DEBUG) {

     HttpLoggingInterceptor.Level.BASIC

 } else {

     HttpLoggingInterceptor.Level.NONE
 }


} }

// ----------------------------------------------- // 3. CONFIGURATION OKHTTP // -----------------------------------------------

/**

Fournit le client HTTP.
OkHttp gère les connexions réseau
et les délais d'attente. */ @Provides @Singleton fun provideOkHttpClient( loggingInterceptor: HttpLoggingInterceptor ): OkHttpClient {

return OkHttpClient.Builder()

 .connectTimeout(
     30,
     TimeUnit.SECONDS
 )

 .readTimeout(
     30,
     TimeUnit.SECONDS
 )

 .writeTimeout(
     30,
     TimeUnit.SECONDS
 )

 .addInterceptor(loggingInterceptor)

 .build()


}

// ----------------------------------------------- // 4. CONFIGURATION RETROFIT // -----------------------------------------------

/**

Fournit une instance Retrofit.
Retrofit utilise OkHttp pour communiquer
avec le serveur distant. */ @Provides @Singleton fun provideRetrofit( okHttpClient: OkHttpClient, gson: Gson ): Retrofit {

return Retrofit.Builder()

 .baseUrl(BASE_URL)

 .client(okHttpClient)

 .addConverterFactory(
     GsonConverterFactory.create(gson)
 )

 .build()


}

// ----------------------------------------------- // 5. FOURNITURE DU SERVICE API // -----------------------------------------------

/**

Fournit l'interface ApiService.
Retrofit génère automatiquement
son implémentation. */ @Provides @Singleton fun provideApiService(