// data/datastore/SettingsDataStore.kt
package com.example.habittracker.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.internal.throwMissingFieldException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.contracts.contract

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SettingsDataStore @Inject constructor(
     @ApplicationContext private val context: Context
) {
    companion object {
        private val REMINDER_ENABLED = booleanPreferencesKey("reminder_enabled")
        private val NAME = stringPreferencesKey("user_name")
        private val ISDARK = booleanPreferencesKey("is_dark_theme")
    }

    val userName: Flow<String> = context.dataStore.data.map {
        preferences -> preferences[NAME] ?: "Не указано"
    }

    val isDarkTheme: Flow<Boolean> = context.dataStore.data.map{
        preferences -> preferences[ISDARK] ?: true
    }

    suspend fun changeIsDark(theme: Boolean){
        context.dataStore.edit { preferences ->
            preferences[ISDARK] = theme
        }
    }
    suspend fun changeUserName(name: String){
        context.dataStore.edit { preferences ->
            preferences[NAME] = name
        }
    }
    suspend fun setReminderEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[REMINDER_ENABLED] = enabled
        }
    }
    val isReminderEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[REMINDER_ENABLED] ?: false }
}