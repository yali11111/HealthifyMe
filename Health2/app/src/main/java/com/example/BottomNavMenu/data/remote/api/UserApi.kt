package com.example.BottomNavMenu.data.remote.api

import com.example.BottomNavMenu.data.remote.dto.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") userId: String
    ): Response<UserResponse>
}
