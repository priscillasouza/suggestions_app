package com.priscilla.suggestionsapp

import android.app.Application
import com.priscilla.suggestionsapp.activity_suggestion.di.activityModule
import com.priscilla.suggestionsapp.data.di.apiModule
import com.priscilla.suggestionsapp.data.di.daoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SuggestionApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@SuggestionApplication)
            modules(
                apiModule,
                daoModule,
                activityModule
            )
        }
    }
}