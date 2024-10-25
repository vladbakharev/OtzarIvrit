package com.vladbakharev.otzarivrit.navigation

import android.transition.Scene
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vladbakharev.otzarivrit.OtzarIvritAddWord
import com.vladbakharev.otzarivrit.OtzarIvritApp
import com.vladbakharev.otzarivrit.navigation.Screen.AddWord.route

@Composable
fun NavigationStack(navController: NavController) {
    val navController = rememberNavController()

   /* NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            OtzarIvritApp(navController = navController)
        }
        composable(route = Screen.AddWord.route) {
            OtzarIvritAddWord(navController = navController)
        }

    }*/

   /* val navigateToNextScreen: (String) -> Unit = { destinationRoute ->
        val currentScreenNum =
            navController.currentBackStackEntry?.arguments?.getString("num") ?: "0"
        val nextScreenNum = currentScreenNum.toInt() + 1
        navController.navigate(destinationRoute.replace("{num}", "$nextScreenNum"))
    }
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.HomeRoot.route) {
            SimpleScreen(
                title = Screen.HomeRoot.title,
                onNavigateToNextScreenClicked = { navigateToNextScreen(Screen.HomeChild.route) }
            )
        }
        *//*composable(Route.HomeChild.route) { backStackEntry ->
            val screenNum = backStackEntry.arguments?.getString("num") ?: "0"
            SimpleScreen(
                title = "${Route.HomeChild.title} $screenNum",
                onNavigateToNextScreenClicked = { navigateToNextScreen(Screen.HomeChild.route) }
            )
        }*//*
        composable(Route.SettingsRoot.route) {
            SimpleScreen(
                title = Route.SettingsRoot.title,
                onNavigateToNextScreenClicked = { navigateToNextScreen(Screen.SettingsChild.route) }
            )
        }
        *//*composable(Screen.SettingsChild.route) { backStackEntry ->
            val screenNum = backStackEntry.arguments?.getString("num") ?: "0"
            SimpleScreen(
                title = "${Screen.SettingsChild.title} $screenNum",
                onNavigateToNextScreenClicked = { navigateToNextScreen(Screen.SettingsChild.route) }
            )
        }*//*
    }*/

}