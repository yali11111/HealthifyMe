package com.example.BottomNavMenu.di

import com.example.health2.data.remote.api.ApiService import com.google.gson.Gson import com.google.gson.GsonBuilder import dagger.Module import dagger.Provides import dagger.hilt.InstallIn import dagger.hilt.components.SingletonComponent import okhttp3.OkHttpClient import retrofit2.Retrofit import retrofit2.converter.gson.GsonConverterFactory import java.util.concurrent.TimeUnit import javax.inject.Singleton

/**

Module de configuration du réseau.
Configure Gson, OkHttp et Retrofit. */ 
@Module 
@InstallIn(SingletonComponent::class) object NetworkModule {

/**

URL de base du serveur Health2.
A remplacer par l'adresse réelle du serveur. */ private const val BASE_URL = "https://api.example.com/"

/**

Configuration Gson. */ 
@Provides 
@Singleton 
fun provideGson(): Gson {

return GsonBuilder() .setDateFormat("yyyy-MM-dd'T'HH:mm:ss") .create() }

/**

Configuration du client HTTP. */ 
@Provides 
@Singleton 
fun provideOkHttpClient(): OkHttpClient {

return OkHttpClient.Builder() .connectTimeout(30, TimeUnit.SECONDS) .readTimeout(30, TimeUnit.SECONDS) .writeTimeout(30, TimeUnit.SECONDS) .retryOnConnectionFailure(true) .build() }

/**

Création de Retrofit. */ 
@Provides 
@Singleton 
fun provideRetrofit( gson: Gson, okHttpClient: OkHttpClient ): Retrofit {

return Retrofit.Builder() .baseUrl(BASE_URL) .client(okHttpClient) .addConverterFactory( GsonConverterFactory.create(gson) ) .build() }

/**

Création du service API. */ 
@Provides 
@Singleton 
fun provideApiService( retrofit: Retrofit ): ApiService {

return retrofit.create(ApiService::class.java) } } 
