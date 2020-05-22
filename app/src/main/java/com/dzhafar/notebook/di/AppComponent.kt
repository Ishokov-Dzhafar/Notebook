package com.dzhafar.notebook.di

import android.app.Application
import android.content.Context
import com.dzhafar.core_db_api.di.AppProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent: AppProvider {
    /*@Component.Builder
    interface Builder {
        fun build(): AppComponent
    })*/
    /*fun inject(fragment: NoteListFragment)
    fun inject(mainActivity: MainActivity)*/

    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider {
            return appComponent ?: DaggerAppComponent
                .builder()
                .application(application.applicationContext)
                .build().also {
                    appComponent = it
                }
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder
        fun build(): AppComponent
    }
}