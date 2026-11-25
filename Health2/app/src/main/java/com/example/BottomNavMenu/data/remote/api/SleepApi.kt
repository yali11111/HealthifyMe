package com.example.BottomNavMenu.data.remote.api

import com.example.BottomNavMenu.data.remote.dto.response.SleepResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SleepApi {

    @GET("sleep")
    suspend fun getSleep(
        @Query("user_id") userId: String
    ): Response<List<SleepResponse>>
}
