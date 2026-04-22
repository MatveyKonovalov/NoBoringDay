package com.example.noboredday.presentation.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noboredday.presentation.view.MainScreen
import com.example.noboredday.presentation.viewmodels.MainViewModel
import java.nio.file.WatchEvent
import com.example.noboredday.R
import com.example.noboredday.domain.models.StateLoading

@Composable
fun HomeScreen(viewModel: MainViewModel) {

    LaunchedEffect(Unit) {
        if (!viewModel.iShowWelcome.value) {
            viewModel.showWelcome()
            viewModel.changeWelcome()
        }
    }
    val textWelcome by viewModel.textWelcome.collectAsStateWithLifecycle()
    val workText by  viewModel.workText.collectAsStateWithLifecycle()
    val state by viewModel.statusLoading.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = if (state is StateLoading.NoWork) textWelcome else workText,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(100.dp)
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(8f)
        ) {
            OutputIdea(viewModel)
        }
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = { viewModel.getNewTask() },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(100.dp)
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(16),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
            shape = RoundedCornerShape(16),
            enabled = state !is StateLoading.Loading,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Text(
                stringResource(R.string.get_idea), textAlign = TextAlign.Center,
                fontSize = 26.sp
            )
        }
    }
}

@Composable
fun OutputIdea(viewModel: MainViewModel) {
    val status by viewModel.statusLoading.collectAsStateWithLifecycle()
    val textIdea by viewModel.textTask.collectAsStateWithLifecycle()

    when (status) {
        StateLoading.Loading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurfaceVariant)
        StateLoading.Success -> Text(
            text = textIdea,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.9f)
                .background(MaterialTheme.colorScheme.background)
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(16),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                .wrapContentHeight(Alignment.CenterVertically)
        )

        StateLoading.Error -> Image(
            painter = painterResource(R.drawable.ic_error_network),
            contentDescription = "Error",
            modifier = Modifier.fillMaxSize(0.7f)
        )

        else -> return
    }
}

