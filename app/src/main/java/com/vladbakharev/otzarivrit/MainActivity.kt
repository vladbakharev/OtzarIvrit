package com.vladbakharev.otzarivrit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.navigation.NavBar
import com.vladbakharev.otzarivrit.navigation.NavigationStack
import com.vladbakharev.otzarivrit.navigation.Screen
import com.vladbakharev.otzarivrit.ui.theme.OtzarIvritTheme
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtzarIvritTheme {
                NavigationStack(navController = NavController(this))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtzarIvritApp(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    onNavigateToNextScreenClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = Screen.AddWord.route) }
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.fab_add))
            }
        },
        bottomBar = {
            NavigationBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OtzarIvritCard()
        }
    }
}

@Composable
fun OtzarIvritCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    modifier = Modifier,
                    text = "Transcription",
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f),
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.End), text = "Word",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End),
                    text = "Translation",
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun AddWord(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = "Word",
                style = MaterialTheme.typography.titleLarge,
            )
            TextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text("Word") }
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = "Translation",
                style = MaterialTheme.typography.titleLarge,
            )
            TextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text("Translation") }
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = "Transcription",
                style = MaterialTheme.typography.titleLarge,
            )
            TextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text("Transcription") }
            )
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.End),
                onClick = { navController.navigate(route = Screen.HomeRoot.route) },
            ) {
                Text("Add")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    onNavigateToNextScreenClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.label_settings)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            NavigationBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Row (
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp),
                    painter = painterResource(R.drawable.baseline_dark_mode_24),
                    contentDescription = stringResource(R.string.dark_theme)
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = stringResource(R.string.dark_theme),
                    style = MaterialTheme.typography.titleLarge
                )
                Switch(
                    modifier = Modifier
                        .padding(16.dp),
                    checked = false,
                    onCheckedChange = {}

                )
            }
            Row (
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(R.string.about)
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.about),
                    style = MaterialTheme.typography.titleLarge
                )

            }
        }
    }
}

@Composable
fun NavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryFlow.map { backStackEntry ->
        backStackEntry.destination.route
    }.collectAsState(initial = Screen.HomeRoot.route)

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

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
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

//PREVIEWS
@Preview(showBackground = true)
@Composable
fun AddWordPreview() {
    OtzarIvritTheme {
        AddWord(navController = NavController(MainActivity()))
    }
}

@Preview
@Composable
fun OtzarIvritCardPreview() {
    OtzarIvritTheme {
        OtzarIvritCard()
    }
}


@Preview(showBackground = true)
@Composable
fun OtzarIvritAppPreview() {
    OtzarIvritTheme {
        NavigationStack(navController = NavController(MainActivity()))
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    OtzarIvritTheme {
        SettingsScreen(
            navController = NavController(MainActivity()),
            title = stringResource(R.string.label_settings),
            onNavigateToNextScreenClicked = {})
    }

}
