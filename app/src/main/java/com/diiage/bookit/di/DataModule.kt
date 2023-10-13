package com.diiage.bookit.di

import android.content.Context
import android.content.SharedPreferences
import com.diiage.bookit.data.remote.API
import com.diiage.bookit.data.remote.repositories.AuthRepositoryImpl
import com.diiage.bookit.data.remote.repositories.PreferenceRepositoryImpl
import com.diiage.bookit.domain.repositories.AuthRepository
import com.diiage.bookit.domain.repositories.PreferenceRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {

    single<SharedPreferences> { get<Context>().getSharedPreferences("bookit_pref", Context.MODE_PRIVATE) }
    single<PreferenceRepository> { PreferenceRepositoryImpl(get()) }

    single { API() }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}