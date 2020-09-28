package com.dzhafar.notes.presentation.vm

import com.dzhafar.coreDbApi.data.dao.NoteDao
import com.dzhafar.coreDbApi.data.entity.NoteEntity
import com.dzhafar.coreDbApi.di.DBApi
import com.dzhafar.notes.data.repository.NoteRepositoryImpl
import com.dzhafar.notes.domain.interactors.NoteInteractorImpl
import com.dzhafar.notes.domain.models.NoteModel
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
class NoteListViewModelTest {

    private lateinit var viewModel: NoteListVM

    private val notes = listOf(
        NoteModel(1L, "text1", 1600715483, "title1", null),
        NoteModel(2L, "text2", 1600715483, "title2", null),
        NoteModel(3L, "text3", 1600715483, "title3", null)
    )

    private val noteEntities = listOf(
        NoteEntity(1L, "text1", 1600715483, "title1", null),
        NoteEntity(2L, "text2", 1600715483, "title2", null),
        NoteEntity(3L, "text3", 1600715483, "title3", null)
    )

    @Mock
    private lateinit var dbApi: DBApi

    @Mock
    lateinit var noteDao: NoteDao

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun init() {
        Mockito.`when`(dbApi.noteDao()).thenReturn(
            noteDao
        )
        Mockito.`when`(dbApi.noteDao().fetchAll()).thenReturn(
            flow { emit(noteEntities) }
        )
        val noteRepository = NoteRepositoryImpl(dbApi)
        val noteInteractor = NoteInteractorImpl(noteRepository)
        viewModel = NoteListVM(noteInteractor)
    }

    @Test
    fun `fetch notes and mapping entity to model success verification`() {
        viewModel.noteModelList.observeForever {
            assertEquals(it, notes)
        }
    }
}