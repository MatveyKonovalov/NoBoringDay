package com.example.noboredday.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.datastore.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : ViewModel() {
    private val _name = MutableStateFlow("Не указано")
    val name = _name.asStateFlow()

    private val _isDark = MutableStateFlow(true)
    val isDark = _isDark.asStateFlow()
    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private fun loadSettings() {
        viewModelScope.launch {
            settingsDataStore.userName.collect { name ->
                _name.value = name
            }
        }
        viewModelScope.launch {
            settingsDataStore.isDarkTheme.collect { theme ->
                _isDark.value = theme
            }
        }
    }

    init {
        loadSettings()
    }

    fun changeName(name: String) {
        viewModelScope.launch {
            settingsDataStore.changeUserName(name)
            _name.value = name

        }
    }

    fun changeTheme() {
        viewModelScope.launch{
            settingsDataStore.changeIsDark(!_isDark.value)

        }
        _isDark.value = !_isDark.value
    }
    fun openDialog() {
        _showDialog.value = true
    }
    fun closeDialog() {
        _showDialog.value = false
    }
}