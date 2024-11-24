package com.vladbakharev.otzarivrit.data

class WordsRepository(private val wordDao: WordDao) {
    suspend fun insertWord(word: Word) = wordDao.insert(word)
    suspend fun updateWord(word: Word) = wordDao.update(word)
    suspend fun deleteWord(word: Word) = wordDao.delete(word)
    fun getWordById(id: Int) = wordDao.getWordById(id)
    fun getAllWordsStream() = wordDao.getAllWords()
    fun getAllWordsById() = wordDao.getAllWordsById()
}