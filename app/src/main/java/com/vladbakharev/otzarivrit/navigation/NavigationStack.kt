package com.vladbakharev.otzarivrit.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vladbakharev.otzarivrit.data.SettingsViewModel
import com.vladbakharev.otzarivrit.data.SettingsViewModelFactory
import com.vladbakharev.otzarivrit.screens.AddWordScreen
import com.vladbakharev.otzarivrit.screens.EditWordScreen
import com.vladbakharev.otzarivrit.screens.FavouritesScreen
import com.vladbakharev.otzarivrit.screens.HomeScreen
import com.vladbakharev.otzarivrit.screens.SettingsScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(context.applicationContext)
    )

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route, enterTransition = { fadeIn() }) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Settings.route, enterTransition = { fadeIn() }) {
            SettingsScreen(navController = navController, settingsViewModel = settingsViewModel)
        }
        composable(
            route = Screen.AddWord.route, enterTransition = { fadeIn() }) {
            AddWordScreen(navController = navController)
        }
        composable(
            route = Screen.Favourites.route, enterTransition = { fadeIn() }) {
            FavouritesScreen(navController = navController)
        }
        composable(
            route = "${Screen.EditWord.route}/{id}",
            enterTransition = { fadeIn() },
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val wordId = backStackEntry.arguments?.getInt("id") ?: 0
            EditWordScreen(navController = navController, id = wordId)
        }
    }
}