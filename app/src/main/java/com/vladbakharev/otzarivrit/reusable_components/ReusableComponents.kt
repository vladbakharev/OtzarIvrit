package com.vladbakharev.otzarivrit.reusable_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.MainActivity
import com.vladbakharev.otzarivrit.R
import com.vladbakharev.otzarivrit.data.Word
import com.vladbakharev.otzarivrit.navigation.NavBar
import com.vladbakharev.otzarivrit.navigation.Screen
import com.vladbakharev.otzarivrit.ui.theme.OtzarIvritTheme
import com.vladbakharev.otzarivrit.ui.viewmodel.OtzarIvritViewModel
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTopAppBar(
    modifier: Modifier = Modifier,
    title: Int
) {
    TopAppBar(
        title = {
            Text(
                stringResource(title)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        modifier = modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun HomeFloatingActionButton(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate(route = Screen.AddWord.route) },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.fab_add))
    }
}

@Composable
fun BasicNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryFlow.map { backStackEntry ->
        backStackEntry.destination.route
    }.collectAsState(initial = Screen.Home.route)

    val items = listOf(
        NavBar.Home,
        NavBar.Collections,
        NavBar.Settings
    )

    var selectedItem by remember { mutableIntStateOf(0) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.rootRoute.route == currentRoute.value) {
            selectedItem = index
        }
    }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    selectedItem = index
                    navController.navigate(item.rootRoute.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardModalBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    word: Word,
    navController: NavController,
    viewModel: OtzarIvritViewModel
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var isDeleteDialogVisible by remember { mutableStateOf(false) }

    ModalBottomSheet(
        modifier = modifier,
        sheetState = modalBottomSheetState,
        onDismissRequest = { onDismissRequest() }
    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        isDeleteDialogVisible = true
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete)
                )
                Text(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.delete)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onDismissRequest()
                        navController.navigate(route = "${Screen.EditWord.route}/${word.id}")
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit)
                )
                Text(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.edit)
                )
            }
        }
    }
    if (isDeleteDialogVisible) {
        DeleteWordDialog(
            onDismissRequest = {
                isDeleteDialogVisible = false
                onDismissRequest()
            },
            word = word,
            viewModel = viewModel,
            onDeleteConfirmed = { onDismissRequest() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteWordDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onDeleteConfirmed: () -> Unit,
    viewModel: OtzarIvritViewModel,
    word: Word
) {
    var openDialog by remember { mutableStateOf(true) }

    BasicAlertDialog(
        onDismissRequest = { openDialog = false },
    ) {
        Surface(
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(28.dp),
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column {
                Text(
                    modifier = modifier
                        .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 16.dp),
                    text = stringResource(R.string.delete_word),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    modifier = modifier
                        .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                    text = stringResource(R.string.delete_word_question),
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { onDismissRequest() }
                    ) {
                        Text(stringResource(R.string.cancel_word_button))
                    }
                    TextButton(
                        onClick = {
                            viewModel.deleteWord(word)
                            onDeleteConfirmed()
                        }
                    ) {
                        Text(stringResource(R.string.delete_word_button))
                    }
                }
            }
        }
    }
}

@Composable
fun CollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
//        items()


    }

}

//PREVIEWS

@Preview(showBackground = true)
@Composable
fun BasicTopAppBarPreview() {
    OtzarIvritTheme {
        BasicTopAppBar(title = R.string.home_title)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeFloatingActionButtonPreview() {
    OtzarIvritTheme {
        HomeFloatingActionButton(NavController(MainActivity()))
    }
}

@Preview
@Composable
fun BasicNavigationBarPreview() {
    OtzarIvritTheme {
        BasicNavigationBar(NavController(MainActivity()))
    }
}