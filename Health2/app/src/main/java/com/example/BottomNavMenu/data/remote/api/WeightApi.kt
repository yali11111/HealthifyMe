package com.example.BottomNavMenu.data.remote.api

import com.example.BottomNavMenu.data.remote.dto.response.WeightResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeightApi {

    @GET("weights")
    suspend fun getWeights(
        @Query("user_id") userId: String
    ): Response<List<WeightResponse>>
}
