package com.priscilla.suggestionsapp.data.di

import androidx.room.Room
import com.priscilla.suggestionsapp.data.infrastruture.local.database.DB_NAME
import com.priscilla.suggestionsapp.data.infrastruture.local.database.SuggestionsActivityDatabase
import org.koin.dsl.module

val daoModule = module {

    single {
        Room.databaseBuilder(
            get(),
            SuggestionsActivityDatabase::class.java,
            DB_NAME
        ).allowMainThreadQueries()
            .build()
    }
    single { get<SuggestionsActivityDatabase>().activityDao() }
}