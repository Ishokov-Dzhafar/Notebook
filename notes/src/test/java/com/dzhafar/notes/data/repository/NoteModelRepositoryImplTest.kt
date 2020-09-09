package com.dzhafar.notes.data.repository

import com.dzhafar.coreDbApi.data.dao.NoteDao
import com.dzhafar.coreDbApi.data.entity.NoteEntity
import com.dzhafar.coreDbApi.di.DBApi
import com.dzhafar.notes.data.expressions.toNoteModel
import com.dzhafar.notes.domain.models.NoteModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class NoteModelRepositoryImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var db: DBApi

    @Mock
    lateinit var noteDao: NoteDao

    lateinit var noteRepositoryImpl: NoteRepositoryImpl

    @Before
    fun initialization() {
        noteRepositoryImpl = NoteRepositoryImpl(db)
    }

    @Test
    fun `fetch Note list success`() {
        val noteList = listOf(
            NoteEntity(
                1,
                "1111",
                11111111111,
                "111"
            ),
            NoteEntity(
                2,
                "2222",
                11111111112,
                "222"
            ),
            NoteEntity(
                3,
                "3333",
                11111111113,
                "333"
            )
        ).toList()
        val noteListMain = noteList.map {
            it.toNoteModel()
        }
        Mockito.`when`(db.noteDao()).thenReturn(noteDao)
        Mockito.`when`(
            db.noteDao()
                .fetchAll()
        ).thenReturn(flowOf(noteList))
        runBlocking {
            val result = noteRepositoryImpl.getNoteList().single()
            assertEquals(noteListMain, result)
        }
    }

    @Test
    fun `insert Note`() {
        val noteEntity = NoteEntity(
            null,
            "2222",
            1111111111,
            "222"
        )
        Mockito.`when`(db.noteDao()).thenReturn(noteDao)
        runBlocking {
            Mockito.`when`(db.noteDao().insertNote(noteEntity)).thenReturn(1)

            val noteId = noteRepositoryImpl.insertNote(noteEntity.toNoteModel())
            assertEquals(noteId, 1)
        }
    }

    @Test
    fun `delete Note`() {
        val noteEntityId = 1.toLong()
        val noteModel = NoteModel(noteEntityId, "2222", 1111111111, "222")
        Mockito.`when`(db.noteDao()).thenReturn(noteDao)
        runBlocking {
            Mockito.`when`(db.noteDao().deleteNoteById(noteEntityId)).then {
                print(it.arguments[0])
                if (it.arguments[0].toString() != noteEntityId.toString()) {
                    throw NullPointerException("This note id not found")
                } else {
                    return@then Unit
                }
            }

            val noteId = noteRepositoryImpl.deleteNote(noteModel)
            assertEquals(noteId, Unit)
        }
    }
}