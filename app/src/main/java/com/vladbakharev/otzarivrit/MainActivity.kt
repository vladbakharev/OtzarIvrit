package com.vladbakharev.otzarivrit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vladbakharev.otzarivrit.data.AppTheme
import com.vladbakharev.otzarivrit.data.SettingsViewModel
import com.vladbakharev.otzarivrit.data.SettingsViewModelFactory
import com.vladbakharev.otzarivrit.navigation.NavigationStack
import com.vladbakharev.otzarivrit.ui.theme.OtzarIvritTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = Color.Transparent.value.toInt()
            ),
            navigationBarStyle = SystemBarStyle.dark(
                scrim = Color.Transparent.value.toInt()
            )
        )
        setContent {
            val settingsViewModel: SettingsViewModel = viewModel(
                factory = SettingsViewModelFactory(applicationContext)
            )
            val theme by settingsViewModel.theme.collectAsState()

            val darkTheme = when (theme) {
                AppTheme.SYSTEM -> isSystemInDarkTheme()
                AppTheme.LIGHT -> false
                AppTheme.DARK -> true
            }

            OtzarIvritTheme(darkTheme = darkTheme){
                NavigationStack()
            }
        }
    }
}