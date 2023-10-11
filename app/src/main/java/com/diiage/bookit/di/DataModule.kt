package com.diiage.bookit.di

import com.diiage.bookit.data.remote.repositories.API
import org.koin.dsl.module


val dataModule = module {

    // ID API
    single { API() }

}