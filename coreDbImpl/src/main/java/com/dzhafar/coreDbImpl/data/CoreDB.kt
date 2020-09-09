package com.dzhafar.coreDbImpl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dzhafar.coreDbApi.data.entity.CalendarDayEntity
import com.dzhafar.coreDbApi.data.entity.NoteEntity
import com.dzhafar.coreDbApi.di.DBApi
import javax.inject.Singleton

@Singleton
@Database(entities = [NoteEntity::class, CalendarDayEntity::class], version = 2)
abstract class CoreDB : RoomDatabase(), DBApi
