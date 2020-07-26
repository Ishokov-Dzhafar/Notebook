package com.dzhafar.coreDbApi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dzhafar.coreDbApi.data.dto.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun fetchAll(): Flow<List<NoteEntity>>

    @Insert
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Query("DELETE FROM Note WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Long)
}