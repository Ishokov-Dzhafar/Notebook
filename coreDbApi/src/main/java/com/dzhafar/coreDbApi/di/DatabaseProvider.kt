package com.dzhafar.coreDbApi.di

import com.dzhafar.coreDbApi.data.dao.NoteDao

interface DatabaseProvider {
    fun noteDao(): NoteDao
    fun dbApi(): DBApi
}