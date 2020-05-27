package com.dzhafar.main.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dzhafar.core_db_api.di.MainNavProvider
import com.dzhafar.core_db_api.di.ProvidersFacade
import com.dzhafar.core_db_api.navigation.note.NoteNavCommand
import com.dzhafar.main.domain.interactors.NoteInteractor
import com.dzhafar.main.domain.models.Note
import javax.inject.Inject

class NoteListVM @Inject constructor(noteInteractor: NoteInteractor, private val mainNavProvider: MainNavProvider) : ViewModel() {

    val noteList: LiveData<List<Note>> = noteInteractor.getNoteList().asLiveData()

    val toCreateNote = mainNavProvider.toCreateNote

}