package com.vladbakharev.otzarivrit.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.R
import com.vladbakharev.otzarivrit.data.Word
import com.vladbakharev.otzarivrit.reusable_components.BasicNavigationBar
import com.vladbakharev.otzarivrit.reusable_components.BasicTopAppBar
import com.vladbakharev.otzarivrit.reusable_components.CardModalBottomSheet
import com.vladbakharev.otzarivrit.reusable_components.HomeFloatingActionButton
import com.vladbakharev.otzarivrit.ui.theme.Black
import com.vladbakharev.otzarivrit.ui.viewmodel.OtzarIvritViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtzarIvritViewModel = viewModel(factory = OtzarIvritViewModel.Factory)
) {
    val wordsList by viewModel.getAllWordsById().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            BasicTopAppBar(title = R.string.home_title)
        },
        floatingActionButton = {
            HomeFloatingActionButton(navController)
        },
        bottomBar = {
            BasicNavigationBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
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

@Composable
fun WordsList(
    words: List<Word>,
    modifier: Modifier = Modifier,
    viewModel: OtzarIvritViewModel = viewModel(factory = OtzarIvritViewModel.Factory),
    navController: NavController
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(words) { word ->
            WordCard(word = word, viewModel = viewModel, navController = navController)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    word: Word,
    viewModel: OtzarIvritViewModel,
    navController: NavController
) {
    var toggleButtonChecked by remember { mutableStateOf(false) }
    var isModalBottomSheetVisible by remember { mutableStateOf(false) }

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
            .combinedClickable(
                onLongClick = {
                    isModalBottomSheetVisible = true
                },
                onClick = {}
            ),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        )
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .weight(0.5f)
                    .fillMaxSize(),
            ) {
                Text(
                    modifier = modifier
                        .align(Alignment.Start),
                    text = word.transcription,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = modifier.padding(20.dp))
                IconToggleButton(
                    modifier = modifier
                        .size(24.dp),
                    checked = toggleButtonChecked,
                    onCheckedChange = { toggleButtonChecked = it }
                ) {
                    if (!toggleButtonChecked) {
                        Icon(
                            modifier = modifier
                                .fillMaxSize(),
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.favourite)
                        )
                    } else {
                        Icon(
                            modifier = modifier
                                .fillMaxSize(),
                            imageVector = Icons.Default.Favorite,
                            contentDescription = stringResource(R.string.favourite)
                        )
                    }
                }
            }
            Column(
                modifier = modifier
                    .weight(0.5f)
                    .fillMaxSize()
            ) {
                Text(
                    modifier = modifier
                        .align(Alignment.End),
                    text = word.word,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = modifier.padding(18.dp))
                Text(
                    modifier = modifier
                        .align(Alignment.End),
                    text = word.translation,
                    textAlign = TextAlign.End,
                )
            }
        }
    }
    if (isModalBottomSheetVisible) {
        CardModalBottomSheet(
            onDismissRequest = { isModalBottomSheetVisible = false },
            word = word,
            viewModel = viewModel,
            navController = navController
        )
    }
}