package com.dzhafar.coreDbApi.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class NoteEntity(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "title") val title: String
)