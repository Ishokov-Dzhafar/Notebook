package com.dzhafar.notes.domain.interactors

import com.dzhafar.notes.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteInteractor {
    fun getNoteList(): Flow<List<NoteModel>>
    suspend fun createNote(noteModel: NoteModel): Long
    suspend fun deleteNote(noteModel: NoteModel)
    suspend fun updateNote(noteModel: NoteModel)
}