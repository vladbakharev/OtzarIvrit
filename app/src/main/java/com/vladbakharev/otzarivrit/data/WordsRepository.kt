package com.vladbakharev.otzarivrit.data

import kotlinx.coroutines.flow.Flow

interface WordsRepository {

    suspend fun insertWord(word: Word)
    suspend fun updateWord(word: Word)
    suspend fun deleteWord(word: Word)
    fun getWordStream(id: Int): Flow<Word?>
    fun getAllWordsStream(): Flow<List<Word>>

}