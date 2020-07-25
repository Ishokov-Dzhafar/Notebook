package com.dzhafar.notebook

import android.app.Application
import com.dzhafar.coreDbApi.di.AppWithFacade
import com.dzhafar.coreDbApi.di.ProvidersFacade
import com.dzhafar.notebook.di.FacadeComponent

class MainApplication : Application(), AppWithFacade {
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