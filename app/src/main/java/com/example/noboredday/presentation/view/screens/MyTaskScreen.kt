package com.example.noboredday.presentation.view.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noboredday.R
import com.example.noboredday.domain.models.Ideas
import com.example.noboredday.presentation.view.components.Title
import com.example.noboredday.presentation.viewmodels.MainViewModel

@Composable
fun MyTasksScreen(viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        viewModel.loadIdeasByBd()
    }
    val ideas by viewModel.loadedIdeas.collectAsStateWithLifecycle()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Title(
            titleId = R.string.ideas_list,
            icon = Icons.Default.DateRange,
            contentDescription = "Ideas list"
        )
        ShowIdeas(ideas, mainViewModel = viewModel)
    }
}

@Composable
private fun ShowIdeas(ideas: List<Ideas>, mainViewModel: MainViewModel) {
    val currentContext = LocalContext.current
    val listScroll = rememberLazyListState()

    LaunchedEffect(ideas) {
        if (ideas.isNotEmpty()) {
            listScroll.scrollToItem(0)
        }
    }

    LaunchedEffect(Unit) {
        mainViewModel.navigationToTranslate.collect { text ->
            openTranslate(
                text = text,
                context = currentContext
            )
        }
    }

    LazyColumn(
        state = listScroll,
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = ideas,
            key = { idea -> idea.key }
        ) { idea ->
            CardIdea(
                idea = idea, buttonFunc = { mainViewModel.openTranslate(idea.title) },
                buttonFuncDelete = { mainViewModel.deleteTask(idea.key) })
        }
    }
}

@Composable
private inline fun CardIdea(
    idea: Ideas,
    crossinline buttonFunc: (String) -> Unit,
    crossinline buttonFuncDelete: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${stringResource(R.string.saved_idea)}: ",
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = idea.title,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { buttonFunc(idea.title) },
                modifier = Modifier
                    .weight(1f)
                    .height(55.dp)
            ) {
                Text(
                    text = stringResource(R.string.open_translate),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Button(
                onClick = { buttonFuncDelete(idea.key) },
                modifier = Modifier
                    .weight(1f)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = stringResource(R.string.delete_idea),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@SuppressLint("QueryPermissionsNeeded")
private fun openTranslate(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_TRANSLATE).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        val fallback = Intent(
            Intent.ACTION_VIEW,
            "https://translate.yandex.ru/?text=${Uri.encode(text)}".toUri()
        )
        context.startActivity(fallback)
    }
}