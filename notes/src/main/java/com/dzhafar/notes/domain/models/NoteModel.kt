package com.dzhafar.notes.domain.models

import java.io.Serializable

data class NoteModel(
    val id: Long?,
    val text: String,
    val date: Long,
    val title: String,
    val dayId: Long?
) : Serializable