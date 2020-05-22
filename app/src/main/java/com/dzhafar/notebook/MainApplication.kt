package com.dzhafar.notebook

import android.app.Application
import android.content.Context
import com.dzhafar.core_db_api.di.AppWithFacade
import com.dzhafar.core_db_api.di.ProvidersFacade
import com.dzhafar.notebook.di.FacadeComponent

class MainApplication: Application(), AppWithFacade {

    /*override fun getFacade(): ProvidersFacade {
        return object : ProvidersFacade {
            override fun provideContext(): Context {
                return applicationContext
            }
        }
    }*/

    companion object {

        private var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }
}