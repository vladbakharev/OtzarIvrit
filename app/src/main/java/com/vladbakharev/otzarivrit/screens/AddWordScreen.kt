package com.vladbakharev.otzarivrit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.R
import com.vladbakharev.otzarivrit.navigation.Screen
import com.vladbakharev.otzarivrit.reusable_components.WordTextField
import com.vladbakharev.otzarivrit.ui.theme.Black
import com.vladbakharev.otzarivrit.ui.theme.DefaultCornerShape
import com.vladbakharev.otzarivrit.ui.theme.White
import com.vladbakharev.otzarivrit.ui.viewmodel.OtzarIvritViewModel

@Composable
fun AddWordScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtzarIvritViewModel = viewModel(factory = OtzarIvritViewModel.Factory)
) {
    var wordInput by remember { mutableStateOf("") }
    var translationInput by remember { mutableStateOf("") }
    var transcriptionInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.add_word),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
            Spacer(modifier = modifier.padding(vertical = 16.dp))
            WordTextField(
                value = wordInput,
                onValueChange = { wordInput = it },
                placeholder = stringResource(R.string.word_label)
            )
            WordTextField(
                value = translationInput,
                onValueChange = { translationInput = it },
                placeholder = stringResource(R.string.translation_label)
            )
            WordTextField(
                value = transcriptionInput,
                onValueChange = { transcriptionInput = it },
                placeholder = stringResource(R.string.transcription_label)
            )
            Button(
                modifier = modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = DefaultCornerShape,
                onClick = {
                    if (wordInput.isNotEmpty() && translationInput.isNotEmpty()
                        && transcriptionInput.isNotEmpty()
                    ) {
                        viewModel.insertWord(wordInput, translationInput, transcriptionInput)
                        navController.navigate(route = Screen.Home.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = White
                )
            ) {
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.add_button),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}