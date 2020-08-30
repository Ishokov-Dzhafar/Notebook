package com.example.calendar.di

import androidx.lifecycle.ViewModel
import com.dzhafar.coreDbApi.viewModel.ViewModelKey
import com.example.calendar.presentation.vm.CalendarFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CalendarFragmentViewModel::class)
    abstract fun bindNoteListVM(viewModel: CalendarFragmentViewModel): ViewModel
}