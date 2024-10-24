package com.vladbakharev.otzarivrit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.vladbakharev.otzarivrit.R

sealed class Screen(val route: String) {
    data object Main : Screen("main_screen")
    data object Add : Screen("add_screen")
}

sealed class NavBar(val route: String, val icon: ImageVector, val label: String) {
    data object Home : NavBar("home", Icons.Default.Home, R.string.label_home.toString())
    data object Collections : NavBar(
        "collections",
        Icons.AutoMirrored.Filled.List,
        R.string.label_collections.toString()
    )

    data object Settings :
        NavBar("settings", Icons.Default.Settings, R.string.label_settings.toString())

}