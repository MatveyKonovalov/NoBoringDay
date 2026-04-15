package com.example.noboredday.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noboredday.domain.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel(){
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()
    private val _iShowWelcome = MutableStateFlow<Boolean>(false)
    val iShowWelcome = _iShowWelcome.asStateFlow()

    private val _textWelcome = MutableStateFlow<String>("Не знаете чем заняться вечером")
    val textWelcome = _textWelcome.asStateFlow()


    fun showWelcome(){
        viewModelScope.launch {
            delay(3000L)
            _textWelcome.value = "Попробуйте найти новое занятие)"
            delay(3000L)
            _textWelcome.value = "Нажми на кнопку ниже"
        }
    }
    fun changeWelcome(){
        _iShowWelcome.value = !_iShowWelcome.value
    }
}