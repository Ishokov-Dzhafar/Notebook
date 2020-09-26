package com.dzhafar.notes.presentation.view

import com.dzhafar.notes.domain.interactors.NoteInteractorImpl
import com.dzhafar.notes.domain.models.NoteModel
import com.dzhafar.notes.domain.repositories.NoteRepository
import com.dzhafar.notes.presentation.vm.NoteListVM
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NoteListFragmentTest {

    private lateinit var viewModel: NoteListVM

    private val notes = listOf(
        NoteModel(1L, "text1", 1600715483, "title1", null),
        NoteModel(2L, "text2", 1600715483, "title2", null),
        NoteModel(3L, "text3", 1600715483, "title3", null)
    )

    @Mock
    private lateinit var noteRepository: NoteRepository

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun init() {
        Mockito.`when`(noteRepository.getNoteList()).thenReturn(
            flow { emit(notes) }
        )
        val noteInteractor = NoteInteractorImpl(noteRepository)
        viewModel = NoteListVM(noteInteractor)
    }

    @Test
    fun loadNotes() {
        viewModel.noteModelList.observeForever {
            assertEquals(it, notes)
        }
    }
}