package com.example.noboredday.domain.repository

import com.example.noboredday.data.dtomodels.IdeasDto
import com.example.noboredday.domain.models.Ideas

interface AppRepositoryInterface {
    suspend fun getRandomIdea(): Ideas
    suspend fun getIdeas(): List<Ideas>

}