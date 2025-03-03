package com.vladbakharev.otzarivrit.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.MainActivity
import com.vladbakharev.otzarivrit.R
import com.vladbakharev.otzarivrit.navigation.Screen
import com.vladbakharev.otzarivrit.reusable_components.BasicNavigationBar
import com.vladbakharev.otzarivrit.reusable_components.BasicTopAppBar
import com.vladbakharev.otzarivrit.reusable_components.HomeFloatingActionButton
import com.vladbakharev.otzarivrit.ui.theme.OtzarIvritTheme

@Composable
fun CollectionsScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = modifier.padding(bottom = 8.dp),
                title = R.string.collections_title
            )
        },
        floatingActionButton = {
           HomeFloatingActionButton(navController)
        },
        bottomBar = {
            BasicNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            FavouritesCard(modifier, navController)
        }
    }
}

@Composable
fun FavouritesCard(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
            .height(120.dp)
            .clickable { navController.navigate(route = Screen.FavouriteWords.route) },
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(R.string.favourite),
                modifier = modifier
                    .weight(0.3f)
                    .fillMaxSize()
            )
            Text(
                text = stringResource(R.string.favourite_card_title),
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .weight(0.7f),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionsScreenPreview() {
    OtzarIvritTheme {
        CollectionsScreen(
            navController = NavController(MainActivity())
        )
    }
}