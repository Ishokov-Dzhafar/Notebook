package com.dzhafar.navigationapi.navigation.notes

import com.dzhafar.navigationapi.navigation.NavigationMediatorParams

interface NavigateToCreateNotesMediator : NavigationMediatorParams<Long>

interface NavigateToEditNoteMediator : NavigationMediatorParams<NavigateToEditNoteMediator.Params> {
    data class Params(val noteId: Long, val dayId: Long)
}