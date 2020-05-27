package com.dzhafar.core_db_api.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dzhafar.core_db_api.data.dto.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun fetchAll(): Flow<List<Note>>

    @Insert
    suspend fun insertNote(note: Note): Long
}