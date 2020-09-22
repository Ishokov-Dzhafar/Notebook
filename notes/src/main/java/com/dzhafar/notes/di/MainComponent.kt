package com.dzhafar.notes.di

import com.dzhafar.coreApi.di.ProvidersFacade
import com.dzhafar.notes.presentation.view.CreateNoteFragment
import com.dzhafar.notes.presentation.view.EditNoteFragment
import com.dzhafar.notes.presentation.view.NoteListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ProvidersFacade::class],
    modules = [InteractorsModule::class, RepositoryModule::class, ViewModelModule::class]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MainComponent {
            return DaggerMainComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(noteListFragment: NoteListFragment)
    fun inject(createNoteFragment: CreateNoteFragment)
    fun inject(editNoteFragment: EditNoteFragment)
}