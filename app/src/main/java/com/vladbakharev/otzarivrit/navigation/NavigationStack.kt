package com.vladbakharev.otzarivrit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vladbakharev.otzarivrit.OtzarIvritAddWord
import com.vladbakharev.otzarivrit.OtzarIvritApp

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
    ) {
        composable(route = Screen.Main.route) {
            OtzarIvritApp(navController = navController)
        }
        composable(route = Screen.Add.route) {
            OtzarIvritAddWord(navController = navController)
        }
    }

}