package com.example.BottomNavMenu.data.remote.api

import com.example.BottomNavMenu.data.remote.dto.response.CaloriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CaloriesApi {

    @GET("calories")
    suspend fun getCalories(
        @Query("user_id") userId: String
    ): Response<CaloriesResponse>
}
