package com.example.BottomNavMenu.data.remote.api

import com.example.BottomNavMenu.data.remote.dto.response.SuggestionResponse
import retrofit2.Response
import retrofit2.http.GET

interface SuggestionApi {

    @GET("suggestions")
    suspend fun getSuggestions(): Response<List<SuggestionResponse>>
}
