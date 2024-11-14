package com.vladbakharev.otzarivrit.navigation

sealed class Screen(val route: String, val title: String) {
    data object HomeRoot : Screen("home", "Home root screen")
    data object HomeChild : Screen("home/{num}", "Home child screen")
    data object CollectionsRoot : Screen("collections", "Collections root screen")
    data object CollectionsChild : Screen("collections/{num}", "Collections child screen")
    data object SettingsRoot : Screen("settings", "Settings root screen")
    data object SettingsChild : Screen("settings/{num}", "Settings child screen")
    data object AddWord : Screen("add", "Add Word")
    data object FavouriteWords : Screen("favourite", "Favourite Words")
}
