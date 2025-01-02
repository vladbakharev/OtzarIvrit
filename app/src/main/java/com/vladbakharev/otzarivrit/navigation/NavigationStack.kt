package com.vladbakharev.otzarivrit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vladbakharev.otzarivrit.screens.AddWordScreen
import com.vladbakharev.otzarivrit.screens.CollectionsScreen
import com.vladbakharev.otzarivrit.screens.EditWordScreen
import com.vladbakharev.otzarivrit.screens.FavouritesScreen
import com.vladbakharev.otzarivrit.screens.HomeScreen
import com.vladbakharev.otzarivrit.screens.SettingsScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Collections.route) {
            CollectionsScreen(navController = navController)
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(route = Screen.AddWord.route) {
            AddWordScreen(navController = navController)
        }
        composable(route = Screen.FavouriteWords.route) {
            FavouritesScreen(navController = navController)
        }
        composable(
            route = "${Screen.EditWord.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val wordId = backStackEntry.arguments?.getInt("id") ?: 0
            EditWordScreen(navController = navController, id = wordId)
        }
    }
}