package com.dzhafar.core_db_impl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dzhafar.core_db_api.data.dto.NoteEntity
import com.dzhafar.core_db_api.di.DBApi
import javax.inject.Singleton

@Singleton
@Database(entities = [NoteEntity::class], version = 1)
abstract class CoreDB : RoomDatabase(), DBApi
