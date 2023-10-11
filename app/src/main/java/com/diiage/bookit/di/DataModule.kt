package com.diiage.bookit.di

import com.diiage.bookit.data.remote.repositories.API
import com.diiage.bookit.data.remote.repositories.PreferenceRepositoryImpl
import com.diiage.bookit.domain.repositories.PreferenceRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {

    single { androidContext() }
    single<PreferenceRepository> { PreferenceRepositoryImpl(get()) }

    single { API() }
}