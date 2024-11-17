package com.vladbakharev.otzarivrit.navigation

sealed class Screen(val route: String, val title: String) {
    data object Home : Screen("home", "Home screen")
    data object Collections : Screen("collections", "Collections screen")
    data object Settings : Screen("settings", "Settings screen")
    data object AddWord : Screen("add", "Add Word")
    data object EditWord : Screen("edit", "Edit Word")
    data object FavouriteWords : Screen("favourite", "Favourite Words")
}
