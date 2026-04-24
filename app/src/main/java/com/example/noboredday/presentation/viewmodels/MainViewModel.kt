package com.example.noboredday.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noboredday.data.AppRepository
import com.example.noboredday.domain.models.Ideas
import com.example.noboredday.domain.models.StateLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _navigateToTranslate = MutableSharedFlow<String>()
    val navigationToTranslate = _navigateToTranslate.asSharedFlow()
    private val _statusLoading = MutableStateFlow<StateLoading>(StateLoading.NoWork)
    val statusLoading = _statusLoading.asStateFlow()

    private val _iShowWelcome = MutableStateFlow<Boolean>(false)
    val iShowWelcome = _iShowWelcome.asStateFlow()

    private val _textWelcome = MutableStateFlow<String>("Не знаете чем заняться вечером")
    val textWelcome = _textWelcome.asStateFlow()

    private val _textTask = MutableStateFlow("")
    val textTask = _textTask.asStateFlow()

    private val _workText = MutableStateFlow("Загрузка интересной идеи ...")
    val workText = _workText.asStateFlow()
    private val _loadedIdeas = MutableStateFlow<List<Ideas>>(emptyList())
    val loadedIdeas = _loadedIdeas.asStateFlow()

    private var loadingTask: Job? = null

    fun showWelcome() {
        viewModelScope.launch {
            delay(3000L)
            _textWelcome.value = "Попробуйте найти новое занятие)"
            delay(3000L)
            _textWelcome.value = "Нажми на кнопку ниже"
        }
    }

    fun changeWelcome() {
        _iShowWelcome.value = !_iShowWelcome.value
    }

    fun getNewTask() {
        loadingTask?.cancel()
        loadingTask = viewModelScope.launch {
            _statusLoading.value = StateLoading.Loading
            _workText.value = "Загрузка интересной идеи ..."
            try {
                val task = appRepository.getRandomIdea()
                _textTask.value = task.title
                _workText.value = "Вот что мы нашли!"
                _statusLoading.value = StateLoading.Success

            } catch (e: Exception) {
                _workText.value = "Ошибка сети. Проверьте подключение к интернету"
                _statusLoading.value = StateLoading.Error
            }

        }
    }

    fun loadIdeasByBd() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _loadedIdeas.value = appRepository.getIdeas()
            }
        }
    }
    fun openTranslate(text: String){
        viewModelScope.launch {
            _navigateToTranslate.emit(text)
        }
    }
}