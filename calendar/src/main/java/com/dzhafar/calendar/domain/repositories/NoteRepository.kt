package com.dzhafar.calendar.domain.repositories

import com.dzhafar.coreDbApi.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun fetchNotesByDayId(dayId: Long): Flow<List<NoteEntity>>

    suspend fun deleteNote(noteEntity: NoteEntity)
}