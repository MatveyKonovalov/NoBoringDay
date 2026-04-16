package com.example.noboredday.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.work.impl.constraints.trackers.NetworkStateTracker
import com.example.habittracker.data.datastore.SettingsDataStore
import com.example.noboredday.data.AppRepository
import com.example.noboredday.data.mappers.TaskMapper
import com.example.noboredday.data.network.NetworkWork
import com.example.noboredday.presentation.view.screens.SettingScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideNetwork() = NetworkWork()

    @Provides
    @Singleton
    fun provideTaskMapper() = TaskMapper()

    @Provides
    @Singleton
    fun provideRepository(taskMapper: TaskMapper, networkWork: NetworkWork) = AppRepository(
        taskMapper=taskMapper,
        networkWork = networkWork
    )
}