package com.dzhafar.core_db_impl.di

import android.content.Context
import androidx.room.Room
import com.dzhafar.core_db_api.data.dao.NoteDao
import com.dzhafar.core_db_api.di.DBApi
import com.dzhafar.core_db_impl.data.CoreDB
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

private const val NOTE_DATABASE_NAME = "NOTE_DB"

@Module
class DBModule {

    @Reusable
    @Provides
    fun bindCoreDB(context: Context): DBApi {
        return Room.databaseBuilder(context, CoreDB::class.java, NOTE_DATABASE_NAME).build()
    }

    @Reusable
    @Provides
    fun bind(dbApi: DBApi): NoteDao {
        return dbApi.noteDao()
    }

    /*@Singleton
    @Binds
    fun bindCoreDB(context: Context): CoreDB {
        return Room.databaseBuilder(context, CoreDB::class.java, NOTE_DATABASE_NAME).build()
    }

    @Singleton
    @Binds
    fun bindDBClientImpl(dbApi: DBApi): DbClientImpl {
        return DbClientImpl(dbApi)
    }

    @Binds
    fun bindDbApi(coreDB: CoreDB): DBApi

    @Binds
    fun bindDbClientApi(dbClientImpl: DbClientImpl): DBClientApi*/

}