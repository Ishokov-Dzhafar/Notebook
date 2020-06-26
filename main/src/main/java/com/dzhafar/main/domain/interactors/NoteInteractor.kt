package com.dzhafar.main.domain.interactors

import com.dzhafar.main.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteInteractor {
    fun getNoteList(): Flow<List<Note>>
    suspend fun createNote(note: Note): Long
    suspend fun deleteNote(note: Note)
}