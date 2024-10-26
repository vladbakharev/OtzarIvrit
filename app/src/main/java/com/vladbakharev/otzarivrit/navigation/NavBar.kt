package com.vladbakharev.otzarivrit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavBar(val title: String, val icon: ImageVector, val rootRoute: Screen) {
     data object Home : NavBar("Home", Icons.Default.Home, Screen.HomeRoot)
     data object Collections : NavBar("Collections", Icons.AutoMirrored.Filled.List, Screen.CollectionsRoot)
     data object Settings : NavBar("Settings", Icons.Default.Settings, Screen.SettingsRoot)
}