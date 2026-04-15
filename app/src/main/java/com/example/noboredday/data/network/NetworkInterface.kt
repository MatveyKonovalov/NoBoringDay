package com.example.noboredday.data.network

import retrofit2.http.GET

interface NetworkInterface {
    @GET
    suspend fun getIdea(): List<IdeasDto>
}