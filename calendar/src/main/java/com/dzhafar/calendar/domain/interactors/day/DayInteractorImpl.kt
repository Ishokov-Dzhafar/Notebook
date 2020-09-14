package com.dzhafar.calendar.domain.interactors.day

import com.dzhafar.calendar.domain.repositories.NoteRepository
import com.dzhafar.coreDbApi.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DayInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : DayInteractor {
    override suspend fun fetchNotesByDayId(dayId: Long): Flow<List<NoteEntity>> =
        repository.fetchNotesByDayId(dayId)

    override suspend fun deleteNote(noteEntity: NoteEntity) =
        repository.deleteNote(noteEntity)
}