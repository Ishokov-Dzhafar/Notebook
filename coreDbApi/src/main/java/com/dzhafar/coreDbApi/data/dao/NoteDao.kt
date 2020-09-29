package com.dzhafar.coreDbApi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.dzhafar.coreDbApi.data.entity.NOTE
import com.dzhafar.coreDbApi.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM $NOTE ORDER BY date DESC")
    fun fetchAll(): Flow<List<NoteEntity>>

    @Insert
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Query("DELETE FROM $NOTE WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Long)

    @Transaction
    @Query("SELECT * FROM $NOTE WHERE dayId = :dayId ORDER BY date DESC")
    fun fetchNotesByDayId(dayId: Long): Flow<List<NoteEntity>>

    @Query("SELECT * FROM $NOTE WHERE id = :noteId")
    suspend fun fetchNoteById(noteId: Long): NoteEntity
}