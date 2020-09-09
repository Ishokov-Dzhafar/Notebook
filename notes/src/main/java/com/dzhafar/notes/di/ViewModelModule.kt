package com.dzhafar.notes.di

import androidx.lifecycle.ViewModel
import com.dzhafar.coreApi.viewModel.ViewModelKey
import com.dzhafar.notes.presentation.vm.CreateNoteVM
import com.dzhafar.notes.presentation.vm.EditNoteViewModel
import com.dzhafar.notes.presentation.vm.NoteListVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NoteListVM::class)
    abstract fun bindNoteListVM(viewModel: NoteListVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateNoteVM::class)
    abstract fun bindCreateNoteVM(viewModel: CreateNoteVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditNoteViewModel::class)
    abstract fun bindEditNoteViewModel(viewModel: EditNoteViewModel): ViewModel
}