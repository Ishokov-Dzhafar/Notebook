package com.dzhafar.coreDbApi.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = NOTE,
    foreignKeys = [ForeignKey(entity = CalendarDayEntity::class, parentColumns = ["id"], childColumns = ["dayId"])]
)
data class NoteEntity(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "dayId") val dayId: Int?
)