package com.dzhafar.notebook.notes_module.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.dzhafar.notebook.MainActivity
import com.dzhafar.notebook.notes_module.screens.CreateNotesFragmentScreen
import com.dzhafar.notebook.notes_module.screens.ListNotesFragmentScreen
import com.dzhafar.notebook.notes_module.screens.NoteItem
import org.junit.Rule
import org.junit.Test
import java.util.UUID

@LargeTest
class CreateNoteUITest {

    private val noteListScreen = ListNotesFragmentScreen()

    private val createNoteScreen = CreateNotesFragmentScreen()

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createNote() {
        val titleText = "UI title ${UUID.randomUUID()}"
        val bodyText = "One life, one love"
        noteListScreen {
            menuAddBtn.click()
        }
        createNoteScreen {
            titleEditText.typeText(titleText)
            bodyEditText.typeText(bodyText)
            saveBtn.click()
        }
        noteListScreen {
            noteList {
                firstChild<NoteItem> {
                    isVisible()
                    title { hasText(titleText) }
                    body { hasText(bodyText) }
                }
            }
        }
    }

}