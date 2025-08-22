package com.vladbakharev.otzarivrit.navigation

import com.vladbakharev.otzarivrit.R

sealed class NavBar(val title: String, val icon: Int, val rootRoute: Screen) {
     data object Home : NavBar("Home", R.drawable.home, Screen.Home)
     data object Favourites : NavBar("Favourites", R.drawable.menu, Screen.Favourites)
     data object Settings : NavBar("Settings", R.drawable.settings, Screen.Settings)
}