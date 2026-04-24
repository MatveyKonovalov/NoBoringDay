package com.example.noboredday.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(): ViewModel(){
    private val _selectedScreen = MutableStateFlow("Home")
    val selectedScreen = _selectedScreen.asStateFlow()

    fun selectScreen(route: String){
        viewModelScope.launch {
            _selectedScreen.value = route
        }
    }
}