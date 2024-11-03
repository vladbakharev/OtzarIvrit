package com.vladbakharev.otzarivrit.data

import android.content.Context

class WordContainer(private val context: Context) {

    val wordsRepository: WordsRepository by lazy {
        WordsRepository(WordsDatabase.getDatabase(context).wordDao())
    }
}