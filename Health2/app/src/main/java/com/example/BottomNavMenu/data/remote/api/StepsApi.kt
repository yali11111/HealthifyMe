package com.example.BottomNavMenu.data.remote.api

import com.example.BottomNavMenu.data.remote.dto.response.StepsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StepsApi {

    @GET("steps")
    suspend fun getSteps(
        @Query("user_id") userId: String
    ): Response<List<StepsResponse>>
}
