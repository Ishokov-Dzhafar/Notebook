package com.dzhafar.main.data.repository

import com.dzhafar.main.domain.models.Note
import com.dzhafar.main.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor() :
    NoteRepository {
    override fun getNoteList(): Flow<List<Note>> {
        //TODO("Not yet implemented")
        return flow {
            emit(listOf<Note>(
                Note(
                    id = 1,
                    text = "123",
                    date = 333333333,
                    title = "1"
                ),
                Note(
                    id = 2,
                    text = "1234",
                    date = 333333350,
                    title = "2"
                )
            ))
        }
    }
}