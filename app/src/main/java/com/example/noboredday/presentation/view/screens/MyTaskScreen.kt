package com.example.noboredday.presentation.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.noboredday.presentation.navigation.Screen
import com.example.noboredday.presentation.view.components.Title
import com.example.noboredday.presentation.viewmodels.MainViewModel
import com.example.noboredday.R

@Composable
fun MyTasksScreen(viewModel: MainViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Title(
            titleId = R.string.ideas_list,
            icon = Icons.Default.DateRange,
            contentDescription = "Ideas list"
        )
        ShowIdeas()
    }

}
@Composable
private fun ShowIdeas(){
    // TODO
}