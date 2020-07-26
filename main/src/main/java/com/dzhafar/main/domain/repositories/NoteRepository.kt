package com.dzhafar.main.domain.repositories

import com.dzhafar.main.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNoteList(): Flow<List<NoteModel>>
    suspend fun insertNote(noteModel: NoteModel): Long
    suspend fun deleteNote(noteModel: NoteModel)
    suspend fun updateNote(noteModel: NoteModel)
}