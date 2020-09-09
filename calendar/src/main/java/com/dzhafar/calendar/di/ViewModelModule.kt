package com.dzhafar.calendar.di

import androidx.lifecycle.ViewModel
import com.dzhafar.coreApi.viewModel.ViewModelKey
import com.dzhafar.calendar.presentation.vm.CalendarFragmentViewModel
import com.dzhafar.calendar.presentation.vm.DayFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CalendarFragmentViewModel::class)
    abstract fun bindNoteListVM(viewModel: CalendarFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DayFragmentViewModel::class)
    abstract fun bindDaFragmentViewModel(viewModel: DayFragmentViewModel): ViewModel
}