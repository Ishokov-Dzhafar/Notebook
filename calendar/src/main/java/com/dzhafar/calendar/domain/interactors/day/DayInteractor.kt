package com.dzhafar.calendar.domain.interactors.day

import com.dzhafar.coreDbApi.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface DayInteractor {
    suspend fun fetchNotesByDayId(dayId: Long): Flow<List<NoteEntity>>

    suspend fun deleteNote(noteEntity: NoteEntity)
}