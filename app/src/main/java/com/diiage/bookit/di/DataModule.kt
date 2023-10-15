package com.diiage.bookit.di

import android.content.Context
import android.content.SharedPreferences
import com.diiage.bookit.data.remote.API
import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.mapper.BookableMapper
import com.diiage.bookit.data.remote.repositories.BookableRepositoryImpl
import com.diiage.bookit.domain.repositories.BookableRepository
import com.diiage.bookit.data.remote.repositories.AuthRepositoryImpl
import com.diiage.bookit.data.remote.repositories.MaterialRepositoryImpl
import com.diiage.bookit.data.remote.repositories.PreferenceRepositoryImpl
import com.diiage.bookit.data.remote.repositories.SearchRepositoryImpl
import com.diiage.bookit.data.remote.repositories.SlotRepositoryImpl
import com.diiage.bookit.domain.repositories.AuthRepository
import com.diiage.bookit.domain.repositories.MaterialRepository
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.domain.repositories.SearchRepository
import com.diiage.bookit.domain.repositories.SlotRepository
import org.koin.dsl.module
import org.mapstruct.factory.Mappers


val dataModule = module {

    single<SharedPreferences> { get<Context>().getSharedPreferences("bookit_pref", Context.MODE_PRIVATE) }
    single<PreferenceRepository> { PreferenceRepositoryImpl(get()) }

    single<BookableMapper> { Mappers.getMapper(BookableMapper::class.java) }

    single { API() }
    single { ApiAuth(get()) }

    single<BookableRepository> { BookableRepositoryImpl(get(), get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<SlotRepository> { SlotRepositoryImpl(get()) }
    single<MaterialRepository> { MaterialRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}