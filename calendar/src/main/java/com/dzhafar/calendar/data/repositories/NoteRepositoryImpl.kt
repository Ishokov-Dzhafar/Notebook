package com.dzhafar.calendar.data.repositories

import com.dzhafar.calendar.domain.repositories.NoteRepository
import com.dzhafar.coreDbApi.data.entity.NoteEntity
import com.dzhafar.coreDbApi.di.DBApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dbApi: DBApi
) : NoteRepository {
    override suspend fun fetchNotesByDayId(dayId: Long): Flow<List<NoteEntity>> =
        dbApi.noteDao().fetchNotesByDayId(dayId)

    override suspend fun deleteNote(noteEntity: NoteEntity) =
        dbApi.noteDao().deleteNoteById(noteEntity.id!!)
}