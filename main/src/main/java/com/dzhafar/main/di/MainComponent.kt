package com.dzhafar.main.di

import com.dzhafar.core_db_api.di.MainNavProvider
import com.dzhafar.core_db_api.di.ProvidersFacade
import com.dzhafar.core_db_api.navigation.NavCommand
import com.dzhafar.core_db_api.navigation.note.NoteNavCommand
import com.dzhafar.main.presentation.MainActivity
import com.dzhafar.main.presentation.view.CreateNoteFragment
import com.dzhafar.main.presentation.view.NoteListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ProvidersFacade::class, MainNavProvider::class],
    modules = [InteractorsModule::class, RepositoryModule::class, ViewModelModule::class]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade, mainNavProvider: MainNavProvider): MainComponent {
            /*val noteNavCommand: NoteNavCommand = object : NoteNavCommand {
                override val toCreateNote = providersFacade.toCreateNote
            }*/
            return DaggerMainComponent.builder()
                .providersFacade(providersFacade)
                .mainNavProvider(mainNavProvider)
                //.noteNavCommand(noteNavCommand)
                .build()
        }
    }

    fun inject(mainActivity: MainActivity)
    fun inject(noteListFragment: NoteListFragment)
    fun inject(createNoteFragment: CreateNoteFragment)
}