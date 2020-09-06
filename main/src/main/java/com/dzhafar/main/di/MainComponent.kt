package com.dzhafar.main.di

import com.dzhafar.coreApi.di.ProvidersFacade
import com.dzhafar.main.presentation.view.CreateNoteFragment
import com.dzhafar.main.presentation.view.EditNoteFragment
import com.dzhafar.main.presentation.view.NoteListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [com.dzhafar.coreApi.di.ProvidersFacade::class],
    modules = [InteractorsModule::class, RepositoryModule::class, ViewModelModule::class]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: com.dzhafar.coreApi.di.ProvidersFacade): MainComponent {
            return DaggerMainComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(noteListFragment: NoteListFragment)
    fun inject(createNoteFragment: CreateNoteFragment)
    fun inject(editNoteFragment: EditNoteFragment)
}