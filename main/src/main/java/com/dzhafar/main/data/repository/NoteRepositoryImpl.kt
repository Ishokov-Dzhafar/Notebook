package com.dzhafar.main.data.repository

import com.dzhafar.core_db_api.di.DBApi
import com.dzhafar.main.domain.models.Note
import com.dzhafar.main.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val db: DBApi) :
    NoteRepository {
    override fun getNoteList(): Flow<List<Note>> {
        return db.noteDao().fetchAll().map { dbNoteList ->
            dbNoteList.map {
                Note(it.id, it.text, it.date, it.title)
            }
        }
    }

    override suspend fun insertNote(note: Note): Long {
        return db.noteDao().insertNote(com.dzhafar.core_db_api.data.dto.Note(
                id = note.id, text = note.text, date = note.date, title = note.title
            ))
    }
}