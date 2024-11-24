package com.vladbakharev.otzarivrit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT * FROM words WHERE id = :id")
    fun getWordById(id: Int): Flow<Word>

    @Query("SELECT * FROM words ORDER BY word ASC")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM words ORDER BY id DESC")
    fun getAllWordsById(): Flow<List<Word>>
}