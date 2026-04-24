package com.example.noboredday.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [IdeaEntity::class],
    version = 1,
)
abstract class DataBase: RoomDatabase(){
    abstract fun ideaDao(): IdeaDao

    companion object{
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getInstance(context: Context): DataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "data_base.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}