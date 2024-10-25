package com.vladbakharev.otzarivrit.navigation

sealed class Screen(val route: String, val title: String) {
    data object Home : Screen("home", "Home")
    data object Collections : Screen("collections", "Collections")
    data object Settings : Screen("settings", "Settings")
    data object AddWord : Screen("add", "Add Word")
}
