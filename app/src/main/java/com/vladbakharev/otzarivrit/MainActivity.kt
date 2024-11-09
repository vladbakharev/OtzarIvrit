package com.vladbakharev.otzarivrit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.data.Word
import com.vladbakharev.otzarivrit.navigation.NavBar
import com.vladbakharev.otzarivrit.navigation.NavigationStack
import com.vladbakharev.otzarivrit.navigation.Screen
import com.vladbakharev.otzarivrit.ui.theme.OtzarIvritTheme
import com.vladbakharev.otzarivrit.ui.viewmodel.OtzarIvritViewModel
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtzarIvritTheme {
                NavigationStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun FloatingActionButton(
    navController: NavController
) {
    FloatingActionButton(
        onClick = { navController.navigate(route = Screen.AddWord.route) },
    ) {
        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.fab_add))
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    onNavigateToNextScreenClicked: () -> Unit,
    viewModel: OtzarIvritViewModel = viewModel(factory = OtzarIvritViewModel.Factory)
) {
    val wordsList by viewModel.getAllWordsById().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(navController)
        },
        bottomBar = {
            NavigationBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            WordsList(wordsList, modifier)
        }
    }
}

@Composable
fun WordsList(
    words: List<Word>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(words) { word ->
            WordCard(word = word)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    word: Word
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
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
            modifier = modifier,
            onDismissRequest = { isModalBottomSheetVisible = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardModalBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
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
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
                Text(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            isDeleteDialogVisible = true
                        },
                    text = stringResource(R.string.delete)
                )
            }
        }
    }
    if (isDeleteDialogVisible) {
        DeleteWordDialog()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteWordDialog(
    modifier: Modifier = Modifier
) {
    var openDialog by remember { mutableStateOf(true) }

    BasicAlertDialog (
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
                Row (
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { openDialog = false }
                    ) {
                        Text(stringResource(R.string.cancel_word_button))
                    }
                    TextButton(
                        onClick = {}
                    ) {
                        Text(stringResource(R.string.delete_word_button))
                    }
                }
            }
        }
    }
}

@Composable
fun AddWord(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtzarIvritViewModel = viewModel(factory = OtzarIvritViewModel.Factory)
) {
    var wordInput by remember { mutableStateOf("") }
    var translationInput by remember { mutableStateOf("") }
    var transcriptionInput by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = modifier
                    .padding(start = 16.dp),
                text = "Word",
                style = MaterialTheme.typography.titleLarge,
            )
            TextField(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = wordInput,
                onValueChange = { wordInput = it },
                label = { Text(stringResource(R.string.word_label)) },
                singleLine = true
            )
            Text(
                modifier = modifier
                    .padding(start = 16.dp),
                text = "Translation",
                style = MaterialTheme.typography.titleLarge,
            )
            TextField(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = translationInput,
                onValueChange = { translationInput = it },
                label = { Text(stringResource(R.string.translation_label)) },
                singleLine = true
            )
            Text(
                modifier = modifier
                    .padding(start = 16.dp),
                text = "Transcription",
                style = MaterialTheme.typography.titleLarge,
            )
            TextField(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = transcriptionInput,
                onValueChange = { transcriptionInput = it },
                label = { Text(stringResource(R.string.transcription_label)) },
                singleLine = true
            )
            Button(
                modifier = modifier
                    .padding(16.dp)
                    .align(Alignment.End),
                onClick = {
                    viewModel.insertWord(wordInput, translationInput, transcriptionInput)
                    navController.navigate(route = Screen.HomeRoot.route)
                },
            ) {
                Text("Add")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionsScreen(
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
                        stringResource(R.string.label_collections)
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
            modifier = modifier.padding(innerPadding)
        ) {
            FavouritesCard(modifier)
        }
    }
}

@Composable
fun FavouritesCard(
    modifier: Modifier = Modifier
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
            .height(120.dp),
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
//                    .size(50.dp)
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
            modifier = modifier.padding(innerPadding)
        ) {
            Row(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = modifier
                        .padding(16.dp),
                    painter = painterResource(R.drawable.baseline_dark_mode_24),
                    contentDescription = stringResource(R.string.dark_theme)
                )
                Text(
                    modifier = modifier
                        .padding(16.dp),
                    text = stringResource(R.string.dark_theme),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = modifier.weight(1f))
                Switch(
                    modifier = modifier
                        .padding(16.dp),
                    checked = false,
                    onCheckedChange = { },
                )
            }
            Row(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(R.string.about)
                )
                Text(
                    modifier = modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.about),
                    fontSize = 18.sp
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
fun WordsListPreview() {
    OtzarIvritTheme {
        WordsList(
            words = listOf(
                Word(
                    word = "בוקר",
                    transcription = "Boker",
                    translation = "Morning"
                ),
                Word(
                    word = "טוב",
                    transcription = "Tov",
                    translation = "Good"
                ),
                Word(
                    word = "אתה",
                    transcription = "Atah",
                    translation = "You"
                ),
                Word(
                    word = "מורה",
                    transcription = "Moreh",
                    translation = "Teacher"
                ),
                Word(
                    word = "תלמיד",
                    transcription = "Tal'mid",
                    translation = "Pupil"
                ),
                Word(
                    word = "חייל",
                    transcription = "Hayal",
                    translation = "Soldier"
                ),
                Word(
                    word = "מים",
                    transcription = "Maim",
                    translation = "Water"
                )
            ),
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionsScreenPreview() {
    OtzarIvritTheme {
        CollectionsScreen(
            navController = NavController(MainActivity()),
            title = stringResource(R.string.label_collections),
            onNavigateToNextScreenClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    TopAppBar()
}

@Preview(showBackground = true)
@Composable
fun FloatingActionButtonPreview() {
    FloatingActionButton(navController = NavController(MainActivity()))
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

@Preview
@Composable
fun WordCardPreview() {
    OtzarIvritTheme {
        WordCard(
            word = Word(
                word = "Word",
                transcription = "Translation",
                translation = "Transcription"
            )
        )
    }
}

@Preview
@Composable
fun FavouritesCardPreview() {
    OtzarIvritTheme {
        FavouritesCard(
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    OtzarIvritTheme {
        NavigationBar(navController = NavController(MainActivity()))
    }
}

@Preview
@Composable
fun CardModalBottomSheetPreview() {
    OtzarIvritTheme {
        CardModalBottomSheet(onDismissRequest = {})
    }
}

@Preview
@Composable
fun DeleteWordDialogPreview() {
    OtzarIvritTheme {
        DeleteWordDialog()
    }
}






