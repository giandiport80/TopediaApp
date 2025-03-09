package com.giandiport80.topediaapp.core.di

import com.giandiport80.topediaapp.core.data.repository.AlamatRepository
import com.giandiport80.topediaapp.core.data.repository.AppRepository
import com.giandiport80.topediaapp.core.data.repository.CategoryRepository
import com.giandiport80.topediaapp.core.data.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
    single { ProductRepository(get(), get()) }
    single { AlamatRepository(get(), get()) }
    single { CategoryRepository(get(), get()) }
}