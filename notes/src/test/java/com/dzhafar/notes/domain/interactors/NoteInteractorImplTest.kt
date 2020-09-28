package com.dzhafar.notes.domain.interactors

import com.dzhafar.notes.domain.models.NoteModel
import com.dzhafar.notes.domain.repositories.NoteRepository
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

class NoteInteractorImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var noteRepository: NoteRepository

    lateinit var noteInteractorImpl: NoteInteractorImpl

    @Before
    fun setUp() {
        noteInteractorImpl = NoteInteractorImpl(noteRepository)
    }

    @Test
    fun `fetch note list success`() {
        val noteList = listOf(
            NoteModel(1, "1111", 11111111111, "111", null),
            NoteModel(2, "2222", 11111111112, "222", null),
            NoteModel(3, "3333", 11111111113, "333", null)
        ).toList()
        Mockito.`when`(noteRepository.getNoteList()).thenReturn(
            flowOf(noteList)
        )
        runBlocking {
            val result = noteInteractorImpl.getNoteList().single()
            assertEquals(noteList, result)
        }
    }

    @Test
    fun `create note`() {
        val note = NoteModel(1, "1111", 11111111111, "111", null)
        runBlocking {
            Mockito.`when`(noteRepository.insertNote(note)).thenReturn(note.id)
            val result = noteInteractorImpl.createNote(note)
            assertEquals(result, note.id)
        }
    }

    @Test
    fun `delete note`() {
        val note = NoteModel(1, "1111", 11111111111, "111", null)
        runBlocking {
            Mockito.`when`(noteRepository.deleteNote(note)).thenReturn(Unit)
            val result = noteInteractorImpl.deleteNote(note)
            assertEquals(result, Unit)
        }
    }
}