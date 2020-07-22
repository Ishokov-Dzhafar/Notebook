package com.dzhafar.core_db_api.di

import com.dzhafar.core_db_api.data.dao.NoteDao

interface DBApi {
    fun noteDao(): NoteDao
}