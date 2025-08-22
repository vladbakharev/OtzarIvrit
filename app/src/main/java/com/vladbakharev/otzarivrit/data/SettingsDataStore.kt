package com.vladbakharev.otzarivrit.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppTheme { SYSTEM, LIGHT, DARK }

val Context.settingsDataStore by preferencesDataStore("settings")

private val THEME_KEY = stringPreferencesKey("app_theme")

class SettingsRepository(private val context: Context) {
    val theme: Flow<AppTheme> = context.settingsDataStore.data
        .map { preferences ->
            when (preferences[THEME_KEY]) {
                "SYSTEM" -> AppTheme.SYSTEM
                "LIGHT" -> AppTheme.LIGHT
                "DARK" -> AppTheme.DARK
                else -> AppTheme.SYSTEM
            }
        }

    suspend fun setTheme(theme: AppTheme) {
        context.settingsDataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }
}

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {
    val theme: StateFlow<AppTheme> = repository.theme.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AppTheme.SYSTEM
    )

    fun updateTheme(theme: AppTheme) {
        viewModelScope.launch {
            repository.setTheme(theme)
        }
    }
}

class SettingsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(SettingsRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}