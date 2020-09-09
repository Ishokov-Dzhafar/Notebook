package com.dzhafar.calendar.di

import com.dzhafar.coreApi.di.ProvidersFacade
import com.dzhafar.calendar.presentation.view.CalendarFragment
import com.dzhafar.calendar.presentation.view.DayFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ProvidersFacade::class], modules = [ViewModelModule::class, InteractorsModule::class]
)
interface CalendarComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): CalendarComponent {
            return DaggerCalendarComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }
    fun inject(fragment: CalendarFragment)
    fun inject(fragment: DayFragment)
}