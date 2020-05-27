package com.dzhafar.main.domain.interactors

import com.dzhafar.main.domain.models.Note
import com.dzhafar.main.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteInteractorImpl @Inject constructor(val noteRepository: NoteRepository): NoteInteractor {

    override fun getNoteList(): Flow<List<Note>> {
        return noteRepository.getNoteList()
    }

    override suspend fun createNote(note: Note): Long {
        return noteRepository.insertNote(note)
    }
}