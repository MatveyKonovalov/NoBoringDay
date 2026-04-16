package com.example.noboredday.data.database

import androidx.room.Entity

@Entity
data class IdeaEntity(
    val key: String,
    val title: String,
    val description: String,
)