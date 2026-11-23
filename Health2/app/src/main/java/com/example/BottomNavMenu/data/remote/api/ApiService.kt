package com.example.health2.data.remote.api

import retrofit2.Response import retrofit2.http.Body import retrofit2.http.DELETE import retrofit2.http.GET import retrofit2.http.POST import retrofit2.http.PUT import retrofit2.http.Path import retrofit2.http.Query

/**

Interface Retrofit permettant de communiquer
avec le serveur distant de l'application Health2.
Les endpoints doivent être adaptés à l'API réelle. */ interface ApiService {

// ================================================== // USER STATISTICS // ==================================================

/**

Récupère toutes les statistiques d'un utilisateur.
GET /users/{userId}/stats */ @GET("users/{userId}/stats") suspend fun getUserStats( @Path("userId") userId: String ): Response<List<UserStatsDto>>

/**

Récupère les statistiques sur une période.
GET /users/{userId}/stats?startDate=...&endDate=... */ @GET("users/{userId}/stats") suspend fun getUserStatsBetweenDates( @Path("userId") userId: String, @Query("startDate") startDate: Long, @Query("endDate") endDate: Long ): Response<List<UserStatsDto>>

/**

Récupère la dernière mesure enregistrée.
GET /users/{userId}/stats/latest */ @GET("users/{userId}/stats/latest") suspend fun getLatestStats( @Path("userId") userId: String ): Response<UserStatsDto>

/**

Enregistre une nouvelle mesure.
POST /users/{userId}/stats */ @POST("users/{userId}/stats") suspend fun createUserStats( @Path("userId") userId: String, @Body stats: UserStatsDto ): Response<UserStatsDto>

/**

Met à jour une mesure existante.
PUT /users/{userId}/stats/{statsId} */ @PUT("users/{userId}/stats/{statsId}") suspend fun updateUserStats( @Path("userId") userId: String, @Path("statsId") statsId: String, @Body stats: UserStatsDto ): Response<UserStatsDto>

/**

Supprime une mesure.
DELETE /users/{userId}/stats/{statsId} */ @DELETE("users/{userId}/stats/{statsId}") suspend fun deleteUserStats( @Path("userId") userId: String, @Path("statsId") statsId: String ): Response<Unit>

/**

Synchronise plusieurs mesures avec le serveur.
POST /users/{userId}/stats/batch */ @POST("users/{userId}/stats/batch") suspend fun uploadMultipleStats( @Path("userId") userId: String, @Body stats: List<UserStatsDto> ): Response<List<UserStatsDto>>

// ================================================== // USER PROFILE // ==================================================

/**

Récupère le profil d'un utilisateur.
GET /users/{userId} */ @GET("users/{userId}") suspend fun getUserProfile( @Path("userId") userId: String ): Response<UserProfileDto>

// ================================================== // HEALTH CHECK // ==================================================

/**

Vérifie si le serveur est disponible.
GET /health */ @GET("health") suspend fun checkServerStatus(): Response<HealthResponse> }
