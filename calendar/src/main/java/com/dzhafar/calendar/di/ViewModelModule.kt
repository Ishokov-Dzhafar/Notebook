package com.dzhafar.calendar.di

import androidx.lifecycle.ViewModel
import com.dzhafar.coreApi.viewModel.ViewModelKey
import com.dzhafar.calendar.presentation.vm.CalendarFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @com.dzhafar.coreApi.viewModel.ViewModelKey(CalendarFragmentViewModel::class)
    abstract fun bindNoteListVM(viewModel: CalendarFragmentViewModel): ViewModel
}