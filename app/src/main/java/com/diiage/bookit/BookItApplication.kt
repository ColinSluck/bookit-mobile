package com.diiage.bookit

import android.app.Application
import com.diiage.bookit.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookItApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BookItApplication)
            modules(dataModule)
        }
    }
}