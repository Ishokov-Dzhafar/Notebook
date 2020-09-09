package com.dzhafar.notes.data.repository

import com.dzhafar.coreDbApi.di.DBApi
import com.dzhafar.notes.data.expressions.toNoteEntity
import com.dzhafar.notes.data.expressions.toNoteModel
import com.dzhafar.notes.domain.models.NoteModel
import com.dzhafar.notes.domain.repositories.NoteRepository
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

    override suspend fun updateNote(noteModel: NoteModel) {
        db.noteDao().updateNote(
            noteModel.toNoteEntity()
        )
    }
}