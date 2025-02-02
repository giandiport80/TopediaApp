package com.giandiport80.topediaapp.core.di

import com.giandiport80.topediaapp.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}