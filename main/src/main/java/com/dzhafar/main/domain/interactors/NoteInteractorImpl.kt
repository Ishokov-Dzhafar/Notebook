package com.dzhafar.main.domain.interactors

import com.dzhafar.main.domain.models.NoteModel
import com.dzhafar.main.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteInteractorImpl @Inject constructor(private val noteRepository: NoteRepository) :
    NoteInteractor {

    override fun getNoteList(): Flow<List<NoteModel>> {
        return noteRepository.getNoteList()
    }

    override suspend fun createNote(noteModel: NoteModel): Long {
        return noteRepository.insertNote(noteModel)
    }

    override suspend fun deleteNote(noteModel: NoteModel) {
        return noteRepository.deleteNote(noteModel)
    }

    override suspend fun updateNote(noteModel: NoteModel) {
        noteRepository.updateNote(noteModel)
    }
}