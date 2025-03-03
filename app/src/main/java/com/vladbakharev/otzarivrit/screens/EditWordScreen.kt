package com.vladbakharev.otzarivrit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vladbakharev.otzarivrit.R
import com.vladbakharev.otzarivrit.navigation.Screen
import com.vladbakharev.otzarivrit.ui.viewmodel.OtzarIvritViewModel

@Composable
fun EditWordScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtzarIvritViewModel = viewModel(factory = OtzarIvritViewModel.Factory),
    id: Int
) {
    val wordEdit by viewModel.getWordById(id).collectAsState(initial = null)

    wordEdit?.let {
        var wordInputEdit by remember { mutableStateOf(it.word) }
        var translationInputEdit by remember { mutableStateOf(it.translation) }
        var transcriptionInputEdit by remember { mutableStateOf(it.transcription) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 16.dp,
                color = MaterialTheme.colorScheme.secondary
            ) {
                Column(
                    modifier = modifier
//                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = modifier
                            .padding(start = 16.dp),
                        text = stringResource(R.string.edit_word),
                        style = MaterialTheme.typography.titleLarge
                    )
                    OutlinedTextField(
                        modifier = modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        value = wordInputEdit,
                        onValueChange = { wordInputEdit = it },
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
                        value = translationInputEdit,
                        onValueChange = { translationInputEdit = it },
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
                        value = transcriptionInputEdit,
                        onValueChange = { transcriptionInputEdit = it },
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
                            viewModel.updateWord(
                                id = id,
                                word = wordInputEdit,
                                translation = translationInputEdit,
                                transcription = transcriptionInputEdit
                            )
                            navController.navigate(route = Screen.Home.route)
                        }
                    ) {
                        Text(stringResource(R.string.edit_button))
                    }
                }
            }
        }
    }
}