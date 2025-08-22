package com.vladbakharev.otzarivrit.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.R
import com.vladbakharev.otzarivrit.reusable_components.BasicNavigationBar
import com.vladbakharev.otzarivrit.reusable_components.BasicTopAppBar
import com.vladbakharev.otzarivrit.reusable_components.HomeFloatingActionButton
import com.vladbakharev.otzarivrit.reusable_components.WordsList
import com.vladbakharev.otzarivrit.ui.viewmodel.OtzarIvritViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtzarIvritViewModel = viewModel(factory = OtzarIvritViewModel.Factory)
) {
    val wordsList by viewModel.getAllWordsById().collectAsState(initial = emptyList())

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BasicTopAppBar(title = R.string.home_title)
        },
        floatingActionButton = {
            HomeFloatingActionButton(navController)
        },
        bottomBar = {
            BasicNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            WordsList(
                words = wordsList,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}