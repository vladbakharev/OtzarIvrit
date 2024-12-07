package com.vladbakharev.otzarivrit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            AddWord(navController = navController)
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