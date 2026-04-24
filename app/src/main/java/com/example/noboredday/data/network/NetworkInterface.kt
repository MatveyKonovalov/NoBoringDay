package com.example.noboredday.data.network

import com.example.noboredday.data.dtomodels.IdeasDto
import retrofit2.http.GET

interface NetworkInterface {
    @GET("random")
    suspend fun getIdea(): IdeasDto
}