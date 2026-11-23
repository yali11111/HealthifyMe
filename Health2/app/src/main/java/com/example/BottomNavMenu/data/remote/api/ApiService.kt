package com.example.health2.data.remote.api

import com.example.health2.data.remote.dto.ExerciseDto
import com.example.health2.data.remote.dto.SuggestionDto
import com.example.health2.data.remote.dto.UserStatsDto
import com.example.health2.data.remote.response.ApiResponse
import com.example.health2.data.remote.response.GetNearbyPlacesData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface Retrofit pour les communications
 * entre Health2 et le serveur distant.
 */
interface ApiService {

    // ======================================
    // STATISTIQUES DE SANTE
    // ======================================

    @GET("api/v1/users/{userId}/stats")
    suspend fun getUserStats(
        @Path("userId") userId: String
    ): ApiResponse<List<UserStatsDto>>

    @GET("api/v1/users/{userId}/stats/{id}")
    suspend fun getUserStatById(
        @Path("userId") userId: String,
        @Path("id") id: String
    ): ApiResponse<UserStatsDto>

    // ======================================
    // SUGGESTIONS
    // ======================================

    @GET("api/v1/suggestions")
    suspend fun getSuggestions(
        @Query("userId") userId: String
    ): ApiResponse<List<SuggestionDto>>

    // ======================================
    // EXERCICES
    // ======================================

    @GET("api/v1/exercises")
    suspend fun getExercises(
        @Query("category") category: String? = null
    ): ApiResponse<List<ExerciseDto>>

    @GET("api/v1/exercises/{id}")
    suspend fun getExerciseById(
        @Path("id") id: String
    ): ApiResponse<ExerciseDto>

    // ======================================
    // LIEUX A PROXIMITE
    // ======================================

    @GET("maps/api/place/nearbysearch/json")
    suspend fun getNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apiKey: String
    ): GetNearbyPlacesData
}
