package com.example.noboredday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noboredday.presentation.view.MainScreen
import com.example.noboredday.presentation.viewmodels.SettingViewModel
import com.example.noboredday.ui.theme.NoBoredDayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingViewModel: SettingViewModel = hiltViewModel()
            val isDarkTheme by settingViewModel.isDark.collectAsStateWithLifecycle()
            NoBoredDayTheme(isDarkTheme) {
                MainScreen(settingViewModel)
            }
        }
    }
}

