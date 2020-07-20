package com.dzhafar.main.data.repository

import com.dzhafar.core_db_api.di.DBApi
import com.dzhafar.main.data.expressions.toNoteEntity
import com.dzhafar.main.data.expressions.toNoteModel
import com.dzhafar.main.domain.models.NoteModel
import com.dzhafar.main.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val db: DBApi) :
    NoteRepository {
    override fun getNoteList(): Flow<List<NoteModel>> {
        return db.noteDao().fetchAll().map { dbNoteList ->
            dbNoteList.map {
                it.toNoteModel()
            }
        }
    }

    override suspend fun insertNote(noteModel: NoteModel): Long {
        return db.noteDao().insertNote(
            noteModel.toNoteEntity()
        )
    }

    override suspend fun deleteNote(noteModel: NoteModel) {
        return db.noteDao().deleteNoteById(noteModel.id!!)
    }
}