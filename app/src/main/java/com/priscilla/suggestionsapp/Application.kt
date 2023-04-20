package com.priscilla.suggestionsapp

import android.app.Application
import com.priscilla.suggestionsapp.data.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Application)
            modules(
                apiModule
            )
        }
    }
}