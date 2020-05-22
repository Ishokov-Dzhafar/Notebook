package com.dzhafar.main.domain.interactors

import com.dzhafar.main.domain.models.Note
import com.dzhafar.main.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteInteractorImpl @Inject constructor(val noteRepository: com.dzhafar.main.domain.repositories.NoteRepository): NoteInteractor {

    override fun getNoteList(): Flow<List<com.dzhafar.main.domain.models.Note>> {
        return noteRepository.getNoteList()
    }
}