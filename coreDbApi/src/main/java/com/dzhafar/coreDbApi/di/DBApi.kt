package com.dzhafar.coreDbApi.di

import com.dzhafar.coreDbApi.data.dao.NoteDao

interface DBApi {
    fun noteDao(): NoteDao
}