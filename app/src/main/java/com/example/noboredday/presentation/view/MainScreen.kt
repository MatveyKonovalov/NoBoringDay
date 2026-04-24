package com.example.noboredday.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noboredday.presentation.navigation.BottomNavigationBar
import com.example.noboredday.presentation.view.screens.HomeScreen
import com.example.noboredday.presentation.view.screens.MyTasksScreen
import com.example.noboredday.presentation.view.screens.SettingScreen
import com.example.noboredday.presentation.viewmodels.MainViewModel
import com.example.noboredday.presentation.viewmodels.NavigationViewModel
import com.example.noboredday.presentation.viewmodels.SettingViewModel

@Composable
fun MainScreen(
    settingViewModel: SettingViewModel,
    viewModel: MainViewModel = hiltViewModel(),
    navigationViewModel: NavigationViewModel = hiltViewModel())
{
    val selectedScreen by navigationViewModel.selectedScreen.collectAsStateWithLifecycle()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier=Modifier.fillMaxSize().padding(top=statusBarHeight)) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                when (selectedScreen) {
                    "Home" -> HomeScreen(viewModel = viewModel)
                    "Settings" -> SettingScreen(settingViewModel = settingViewModel)
                    "MyTasks" -> MyTasksScreen(viewModel = viewModel)

                }
            }

            BottomNavigationBar(
                currentRoute = selectedScreen,
                onItemSelected = { route ->
                    navigationViewModel.selectScreen(route)
                }
            )
        }
    }
}