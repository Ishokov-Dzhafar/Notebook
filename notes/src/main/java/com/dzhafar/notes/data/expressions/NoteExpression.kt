package com.dzhafar.notes.data.expressions

import com.dzhafar.coreDbApi.data.entity.NoteEntity
import com.dzhafar.notes.domain.models.NoteModel

fun NoteEntity.toNoteModel() = NoteModel(
    id = id,
    text = text,
    date = date,
    title = title,
    dayId = dayId
)

fun NoteModel.toNoteEntity() = NoteEntity(
    id = id,
    text = text,
    date = date,
    title = title,
    dayId = dayId
)