package com.vladbakharev.otzarivrit.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vladbakharev.otzarivrit.data.Word
import com.vladbakharev.otzarivrit.data.WordApplication
import com.vladbakharev.otzarivrit.data.WordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OtzarIvritViewModel(private val wordsRepository: WordsRepository) : ViewModel() {

    fun insertWord(word: String, translation: String, transcription: String) =
        viewModelScope.launch {
            wordsRepository.insertWord(
                Word(
                    word = word,
                    translation = translation,
                    transcription = transcription
                )
            )
        }

    fun updateWord(word: String, translation: String, transcription: String) =
        viewModelScope.launch {
            wordsRepository.updateWord(
                Word(
                    word = word,
                    translation = translation,
                    transcription = transcription
                )
            )
        }

    fun deleteWord(word: String, translation: String, transcription: String) =
        viewModelScope.launch {
            wordsRepository.deleteWord(
                Word(
                    word = word,
                    translation = translation,
                    transcription = transcription
                )
            )
        }

    fun getWord(): Flow<Word?> = wordsRepository.getWordStream(id = 0)

    fun getAllWords(): Flow<List<Word>> = wordsRepository.getAllWordsStream()

    fun getAllWordsById(): Flow<List<Word>> = wordsRepository.getAllWordsById()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as WordApplication)
                OtzarIvritViewModel(application.container.wordsRepository)
            }
        }
    }
}