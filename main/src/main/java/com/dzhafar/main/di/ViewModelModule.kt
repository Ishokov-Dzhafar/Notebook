package com.dzhafar.main.di

import androidx.lifecycle.ViewModel
import com.dzhafar.coreDbApi.viewModel.ViewModelKey
import com.dzhafar.main.presentation.vm.CreateNoteVM
import com.dzhafar.main.presentation.vm.EditNoteViewModel
import com.dzhafar.main.presentation.vm.NoteListVM
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