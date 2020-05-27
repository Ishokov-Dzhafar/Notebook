package com.dzhafar.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dzhafar.core_db_api.di.AppProvider
import com.dzhafar.core_db_api.di.AppWithFacade
import com.dzhafar.core_db_api.navigation.note.NoteNavCommand
import com.dzhafar.main.R
import com.dzhafar.main.di.MainComponent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade(), (application as AppWithFacade).getMainNavProvider()).inject(this)
        //AppComponent.create(((application as MainApplication) as AppWithFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_main)
    }
}
