package com.example.noboredday.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.GET

@Dao
interface IdeaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdea(idea: IdeaEntity): Long

    @Query("SELECT * FROM ideas WHERE `key` = :key")
    suspend fun getIdeaByKey(key: String): List<IdeaEntity>

    @Query("SELECT * FROM ideas")
    suspend fun getAllIdeas(): List<IdeaEntity>
}