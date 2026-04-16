package com.example.noboredday.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noboredday.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel(){

    private val _iShowWelcome = MutableStateFlow<Boolean>(false)
    val iShowWelcome = _iShowWelcome.asStateFlow()

    private val _textWelcome = MutableStateFlow<String>("Не знаете чем заняться вечером")
    val textWelcome = _textWelcome.asStateFlow()

    private val _textTask = MutableStateFlow("")
    val textTask = _textTask.asStateFlow()

    private val _textDescription = MutableStateFlow("")
    val textDescription = _textDescription.asStateFlow()
    private val _ideaAlpha = MutableStateFlow(0f)
    val ideaAlpha = _ideaAlpha.asStateFlow()

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

    fun getNewTask(){
        viewModelScope.launch {
            _textWelcome.value = "Загрузка"
            val task = appRepository.getRandomIdea()
            _textTask.value = task.title
            _ideaAlpha.value = 1f
            _textWelcome.value = "Успешно"
        }
    }
}