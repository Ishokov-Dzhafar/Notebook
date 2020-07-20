package com.dzhafar.main.domain.interactors

import com.dzhafar.main.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteInteractor {
    fun getNoteList(): Flow<List<NoteModel>>
    suspend fun createNote(noteModel: NoteModel): Long
    suspend fun deleteNote(noteModel: NoteModel)
}