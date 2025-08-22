package com.vladbakharev.otzarivrit.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.vladbakharev.otzarivrit.reusable_components.WordsList
import com.vladbakharev.otzarivrit.ui.viewmodel.OtzarIvritViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtzarIvritViewModel = viewModel(factory = OtzarIvritViewModel.Factory)
) {
    val favouritesList by viewModel.getFavouriteWords().collectAsState(initial = emptyList())

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BasicTopAppBar(title = R.string.label_favourites)
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
                words = favouritesList,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}