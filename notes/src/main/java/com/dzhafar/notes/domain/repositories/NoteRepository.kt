package com.dzhafar.notes.domain.repositories

import com.dzhafar.notes.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNoteList(): Flow<List<NoteModel>>
    suspend fun insertNote(noteModel: NoteModel): Long
    suspend fun deleteNote(noteModel: NoteModel)
    suspend fun updateNote(noteModel: NoteModel)
}