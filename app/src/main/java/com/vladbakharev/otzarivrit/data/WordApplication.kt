package com.vladbakharev.otzarivrit.data

import android.app.Application

class WordApplication: Application()  {
    lateinit var container: WordContainer

    override fun onCreate() {
        super.onCreate()
        container = WordContainer(this)
    }
}