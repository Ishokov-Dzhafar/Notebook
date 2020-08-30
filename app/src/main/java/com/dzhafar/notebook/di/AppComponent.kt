package com.dzhafar.notebook.di

import android.app.Application
import android.content.Context
import com.dzhafar.coreDbApi.di.AppProvider
import com.dzhafar.notebook.R
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent : AppProvider {
    companion object {
        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider {
            return appComponent ?: DaggerAppComponent
                .builder()
                .context(application.applicationContext)
                //.hostViewId(R.id.nav_host_fragment)
                .build().also {
                    appComponent = it
                }
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        /*@BindsInstance
        fun hostViewId(hostViewId: Int): Builder*/
        fun build(): AppComponent
    }
}