package com.example.noboredday.domain.models

import java.lang.Exception

sealed class StateLoading{
    object NoWork: StateLoading()
    object Loading: StateLoading()
    object Error: StateLoading()
    object Success: StateLoading()
}