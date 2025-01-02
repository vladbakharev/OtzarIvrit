package com.vladbakharev.otzarivrit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.R
import com.vladbakharev.otzarivrit.navigation.Screen
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
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(500.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background,
            shadowElevation = 16.dp,
            tonalElevation = 16.dp
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
                    text = stringResource(R.string.add_word),
                    style = MaterialTheme.typography.titleLarge,
                )
                OutlinedTextField(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    value = wordInput,
                    onValueChange = { wordInput = it },
                    label = { Text(stringResource(R.string.word_label)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                OutlinedTextField(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    value = translationInput,
                    onValueChange = { translationInput = it },
                    label = { Text(stringResource(R.string.translation_label)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                OutlinedTextField(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    value = transcriptionInput,
                    onValueChange = { transcriptionInput = it },
                    label = { Text(stringResource(R.string.transcription_label)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Default
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                Button(
                    modifier = modifier
                        .padding(16.dp)
                        .align(Alignment.End),
                    onClick = {
                        if (wordInput.isNotEmpty() && translationInput.isNotEmpty()
                            && transcriptionInput.isNotEmpty()
                        ) {
                            viewModel.insertWord(wordInput, translationInput, transcriptionInput)
                            navController.navigate(route = Screen.Home.route)
                        }
                    }
                ) {
                    Text(stringResource(R.string.add_button))
                }
            }
        }
    }
}