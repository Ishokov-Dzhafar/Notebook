package com.dzhafar.main.di

import androidx.lifecycle.ViewModel
import com.dzhafar.core_db_api.view_model.ViewModelKey
import com.dzhafar.main.presentation.vm.CreateNoteVM
import com.dzhafar.main.presentation.vm.NoteListVM
import dagger.Binds
import dagger.Module
import dagger.Provides
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
}