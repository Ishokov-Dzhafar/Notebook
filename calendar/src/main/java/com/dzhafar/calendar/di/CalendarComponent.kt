package com.dzhafar.calendar.di

import com.dzhafar.coreApi.di.ProvidersFacade
import com.dzhafar.calendar.presentation.view.CalendarFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [com.dzhafar.coreApi.di.ProvidersFacade::class], modules = [ViewModelModule::class, InteractorsModule::class]
)
interface CalendarComponent {

    companion object {

        fun create(providersFacade: com.dzhafar.coreApi.di.ProvidersFacade): CalendarComponent {
            return DaggerCalendarComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }
    fun inject(fragment: CalendarFragment)
}