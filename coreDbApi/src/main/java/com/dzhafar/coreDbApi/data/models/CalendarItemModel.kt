package com.dzhafar.coreDbApi.data.models

import androidx.room.Embedded
import androidx.room.Relation
import com.dzhafar.coreDbApi.data.entity.CalendarDayEntity
import com.dzhafar.coreDbApi.data.entity.NoteEntity

data class CalendarItemModel(
    @Embedded
    val calendarDayEntity: CalendarDayEntity,
    @Relation(parentColumn = "id", entityColumn = "dayId", entity = NoteEntity::class)
    val notes: List<NoteEntity>
)