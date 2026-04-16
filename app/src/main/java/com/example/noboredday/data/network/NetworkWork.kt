package com.example.noboredday.data.network

import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import com.example.noboredday.data.dtomodels.IdeasDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkWork @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bored-api.appbrewery.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService = retrofit.create(NetworkInterface::class.java)
    suspend fun getIdea(): List<IdeasDto>? {
        return runCatching {
            val idea = apiService.getIdea()
            listOf(idea)
        }.onFailure {
            throw it
        }.getOrNull()
    }
}