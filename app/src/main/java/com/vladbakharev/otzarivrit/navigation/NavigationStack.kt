package com.vladbakharev.otzarivrit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vladbakharev.otzarivrit.AddWord
import com.vladbakharev.otzarivrit.OtzarIvritApp
import com.vladbakharev.otzarivrit.SettingsScreen

@Composable
fun NavigationStack(navController: NavController) {
    val navController = rememberNavController()

    val navigateToNextScreen: (String) -> Unit = { destinationRoute ->
        val currentScreenNum =
            navController.currentBackStackEntry?.arguments?.getString("num") ?: "0"
        val nextScreenNum = currentScreenNum.toInt() + 1
        navController.navigate(destinationRoute.replace("{num}", "$nextScreenNum"))
    }
    NavHost(navController = navController, startDestination = Screen.HomeRoot.route) {
        composable(route = Screen.HomeRoot.route) {
            OtzarIvritApp(
                navController = navController,
                title = Screen.HomeRoot.title,
                onNavigateToNextScreenClicked = { navigateToNextScreen(Screen.HomeChild.route) }
            )
        }
        composable(Screen.HomeChild.route) { backStackEntry ->
            val screenNum = backStackEntry.arguments?.getString("num") ?: "0"
            OtzarIvritApp(
                navController = navController,
                title = "${Screen.HomeChild.title} $screenNum",
                onNavigateToNextScreenClicked = { navigateToNextScreen(Screen.HomeChild.route) }
            )
        }
        composable(Screen.SettingsRoot.route) {
            SettingsScreen(
                navController = navController,
                title = Screen.SettingsRoot.title,
                onNavigateToNextScreenClicked = { navigateToNextScreen(Screen.SettingsChild.route) }
            )
        }
        composable(Screen.SettingsChild.route) { backStackEntry ->
            val screenNum = backStackEntry.arguments?.getString("num") ?: "0"
            SettingsScreen (
                navController = navController,
                title = "${Screen.SettingsChild.title} $screenNum",
                onNavigateToNextScreenClicked = { navigateToNextScreen(Screen.SettingsChild.route) }
            )
        }
        composable(Screen.AddWord.route) {
            AddWord(navController = navController)
        }
    }

}