package com.dzhafar.notebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dzhafar.calendar.di.CalendarComponent
import com.dzhafar.coreDbApi.di.AppWithFacade
import com.dzhafar.main.di.MainComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade())
        CalendarComponent.create((application as AppWithFacade).getFacade())
        setContentView(R.layout.activity_main)
    }
}