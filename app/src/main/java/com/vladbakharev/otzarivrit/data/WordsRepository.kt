package com.vladbakharev.otzarivrit.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class WordsRepository(private val wordDao: WordDao) {
    suspend fun insertWord(word: Word) = wordDao.insert(word)
    suspend fun updateWord(word: Word) = wordDao.update(word)
    suspend fun deleteWord(word: Word) = wordDao.delete(word)
    suspend fun toggleFavourite(wordId: Int, isFavourite: Boolean) {
        val word = wordDao.getWordById(wordId).first()
        wordDao.update(word.copy(isFavourite = isFavourite))
    }

    fun getWordById(id: Int) = wordDao.getWordById(id)
    fun getAllWordsById() = wordDao.getAllWordsById()
    fun getFavouriteWords(): Flow<List<Word>> {
        return wordDao.getAllWords().map { words ->
            words.filter { it.isFavourite }
        }
    }
}