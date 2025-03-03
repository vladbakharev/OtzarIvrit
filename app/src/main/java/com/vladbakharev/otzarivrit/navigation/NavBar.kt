package com.vladbakharev.otzarivrit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.vladbakharev.otzarivrit.R

sealed class NavBar(val title: String, val icon: Int, val rootRoute: Screen) {
     data object Home : NavBar("Home", R.drawable.home, Screen.Home)
     data object Collections : NavBar("Collections", R.drawable.menu, Screen.Collections)
     data object Settings : NavBar("Settings", R.drawable.settings, Screen.Settings)
}