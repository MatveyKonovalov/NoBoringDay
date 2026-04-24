package com.example.noboredday.domain.models

import androidx.compose.runtime.Immutable

@Immutable
data class Ideas(
    val title: String,
    val description: String,
    val key: String,
)