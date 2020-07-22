package com.dzhafar.main.domain.repositories

import com.dzhafar.main.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNoteList(): Flow<List<Note>>
    suspend fun insertNote(note: Note): Long
    suspend fun deleteNote(note: Note)
}