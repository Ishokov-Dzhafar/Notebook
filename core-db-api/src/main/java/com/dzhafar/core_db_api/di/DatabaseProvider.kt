package com.dzhafar.core_db_api.di

import com.dzhafar.core_db_api.data.dao.NoteDao

interface DatabaseProvider {
    fun noteDao(): NoteDao
    fun dbApi(): DBApi
}