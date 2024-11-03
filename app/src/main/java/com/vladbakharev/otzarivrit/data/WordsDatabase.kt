package com.vladbakharev.otzarivrit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordsDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var Instance: WordsDatabase? = null
        fun getDatabase(context: Context): WordsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WordsDatabase::class.java, "words")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}