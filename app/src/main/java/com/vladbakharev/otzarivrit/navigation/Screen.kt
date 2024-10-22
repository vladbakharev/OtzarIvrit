package com.vladbakharev.otzarivrit.navigation

sealed class Screen (val route: String) {
    data object Main : Screen("main_screen")
    data object Add : Screen("add_screen")
}