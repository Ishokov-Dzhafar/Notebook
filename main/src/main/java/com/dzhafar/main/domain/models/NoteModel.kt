package com.dzhafar.main.domain.models

import java.io.Serializable

data class NoteModel(
    val id: Long?,
    val text: String,
    val date: Long,
    val title: String,
    val dayId: Int?
) : Serializable