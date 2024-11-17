package com.vladbakharev.otzarivrit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vladbakharev.otzarivrit.AddWord
import com.vladbakharev.otzarivrit.CollectionsScreen
import com.vladbakharev.otzarivrit.EditWordScreen
import com.vladbakharev.otzarivrit.FavouritesScreen
import com.vladbakharev.otzarivrit.HomeScreen
import com.vladbakharev.otzarivrit.SettingsScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Collections.route) {
            CollectionsScreen(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(Screen.AddWord.route) {
            AddWord(navController = navController)
        }
        composable(Screen.FavouriteWords.route) {
            FavouritesScreen(navController = navController)
        }
      /*  composable(Screen.EditWord.route) {
            EditWordScreen(navController = navController)
        }*/
    }

}