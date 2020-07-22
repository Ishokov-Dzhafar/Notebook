package com.dzhafar.core_db_api.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dzhafar.core_db_api.data.dto.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun fetchAll(): Flow<List<NoteEntity>>

    @Insert
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Query("Delete from note where id = :noteId")
    suspend fun deleteNoteById(noteId: Long)
}