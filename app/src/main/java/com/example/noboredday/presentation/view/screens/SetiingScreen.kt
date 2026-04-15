package com.example.noboredday.presentation.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noboredday.presentation.viewmodels.SettingViewModel
import com.example.noboredday.presentation.view.components.NameDialog  // ✅ Импорт диалога
import com.example.noboredday.R

@Composable
fun SettingScreen(settingViewModel: SettingViewModel) {

    val showDialog by settingViewModel.showDialog.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()

        OptionsSetting { ShowUserInfo(settingViewModel) }
        Spacer(modifier = Modifier.padding(2.dp))

        OptionsSetting { ColorScheme(settingViewModel) }
        Spacer(modifier = Modifier.padding(2.dp))

        OptionsSetting { VersionApp() }
    }
    if (showDialog) {
        NameDialog(
            onConfirm = { newName ->
                settingViewModel.changeName(newName)
                settingViewModel.closeDialog()
            },
            onDismiss = {
                settingViewModel.closeDialog()
            },
        )
    }
}

@Composable
private fun OptionsSetting(content: @Composable () -> Unit) = Surface(
    modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxWidth(0.9f)
        .height(60.dp)
        .clip(RoundedCornerShape(16)),
    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    color = MaterialTheme.colorScheme.surfaceContainer,
    content = content
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = "Настройки"
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            "Настройки",
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ShowUserInfo(
    settingViewModel: SettingViewModel
) {
    val name by settingViewModel.name.collectAsStateWithLifecycle()  

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Icon(
            imageVector = Icons.Default.AccountCircle,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = "Профиль пользователя",
            modifier = Modifier.size(45.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Column {
            Text(text = name, fontSize = 19.sp)  
            Text(
                text = stringResource(R.string.user_data),
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { settingViewModel.openDialog() }, 
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Text(
                stringResource(R.string.change_name),
                fontSize = 13.sp
            )
        }
        Spacer(modifier = Modifier.padding(3.dp))
    }
}

@Composable
private fun ColorScheme(
    settingViewModel: SettingViewModel
) {
    val isDark by settingViewModel.isDark.collectAsStateWithLifecycle()  

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Icon(
            painter = painterResource(
                id = if (isDark) R.drawable.ic_dark_theme else R.drawable.ic_light_theme
            ),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = "Тема",
            modifier = Modifier.size(45.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Column {
            Text(
                text = if (isDark) stringResource(R.string.dark_theme) else stringResource(R.string.light_theme),
                fontSize = 19.sp
            )
            Text(
                text = if (isDark) stringResource(R.string.on_dark_theme) else stringResource(R.string.on_light_theme),
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = isDark,
            onCheckedChange = { settingViewModel.changeTheme() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = MaterialTheme.colorScheme.surface
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun VersionApp() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Icon(
            imageVector = Icons.Default.Info,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = "Версия",
            modifier = Modifier.size(45.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Column {
            Text(text = "No bored day", fontSize = 19.sp)  
            Text(
                text = "Версия ${stringResource(R.string.version)}",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}