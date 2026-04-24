package com.example.noboredday.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ideas")
data class IdeaEntity(
    @ColumnInfo("key")
    @PrimaryKey
    val key: String,
    val title: String,
    val description: String,
)