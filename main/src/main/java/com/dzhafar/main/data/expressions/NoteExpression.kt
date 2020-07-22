package com.dzhafar.main.data.expressions

import com.dzhafar.core_db_api.data.dto.NoteEntity
import com.dzhafar.main.domain.models.NoteModel

fun NoteEntity.toNoteModel() = NoteModel(
    id = id,
    text = text,
    date = date,
    title = title
)

fun NoteModel.toNoteEntity() = NoteEntity(
    id = id,
    text = text,
    date = date,
    title = title
)