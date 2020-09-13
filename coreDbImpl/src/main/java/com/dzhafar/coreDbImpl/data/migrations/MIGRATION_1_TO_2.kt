package com.dzhafar.coreDbImpl.data.migrations

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dzhafar.coreDbApi.data.entity.CALENDAR_DAY
import com.dzhafar.coreDbApi.data.entity.NOTE

val MIGRATION_1_TO_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        Log.e("TAG", "EXEC MIGRATION")
        database.execSQL(
            """
                CREATE TABLE $CALENDAR_DAY(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                year INTEGER NOT NULL,
                month INTEGER NOT NULL,
                day INTEGER NOT NULL)
            """
        )
        database.execSQL(
            """
                ALTER TABLE $NOTE ADD COLUMN dayId INTEGER REFERENCES $CALENDAR_DAY(id)
            """
        )
        database.execSQL(
            """
                CREATE INDEX dayId ON $CALENDAR_DAY(dayId)
            """
        )
    }
}