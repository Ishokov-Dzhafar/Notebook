package com.dzhafar.main.domain.interactors

import com.dzhafar.main.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteInteractor {
    fun getNoteList() : Flow<List<com.dzhafar.main.domain.models.Note>>
}