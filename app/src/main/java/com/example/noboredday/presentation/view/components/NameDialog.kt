package com.example.noboredday.presentation.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun NameDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("Изменить имя") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth())
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            )
            {
                OutlinedTextField(
                    value = name,
                    onValueChange = {name = it},
                    label = {Text("Новое имя")},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (title.isNotBlank()){
                    onConfirm(name)
                }
            }) {
                Text("Поменять")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        },
        shape = RoundedCornerShape(16)
    )

}