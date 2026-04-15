package com.example.noboredday.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
){
    object Home: Screen(
        route = "Home",
        title = "Главная",
        icon = Icons.Default.Home,
        selectedIcon = Icons.Default.Home
    )
    object MyTasks: Screen(
        route = "MyTasks",
        title = "Мои задачи",
        icon = Icons.Default.DateRange,
        selectedIcon = Icons.Default.DateRange
    )
    object Setting: Screen(
        route = "Settings",
        title = "Настройки",
        icon = Icons.Default.Settings,
        selectedIcon = Icons.Default.Settings
    )
    companion object{
        val allScreens = listOf(Home, MyTasks, Setting)
    }
}