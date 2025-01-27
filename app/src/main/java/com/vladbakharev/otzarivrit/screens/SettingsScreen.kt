package com.vladbakharev.otzarivrit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.MainActivity
import com.vladbakharev.otzarivrit.R
import com.vladbakharev.otzarivrit.reusable_components.BasicNavigationBar
import com.vladbakharev.otzarivrit.reusable_components.BasicTopAppBar
import com.vladbakharev.otzarivrit.ui.theme.OtzarIvritTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var isDarkTheme by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BasicTopAppBar(title = R.string.settings_title)
        },
        bottomBar = {
            BasicNavigationBar(navController)
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
                    checked = isDarkTheme,
                    onCheckedChange = { isDarkTheme = it },
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
    OtzarIvritTheme(darkTheme = isDarkTheme) {
        // Content with the selected theme
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    OtzarIvritTheme {
        SettingsScreen(
            navController = NavController(MainActivity())
        )
    }
}