package com.dzhafar.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dzhafar.core_db_api.di.AppWithFacade
import com.dzhafar.main.R
import com.dzhafar.main.di.MainComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_main)
    }
}