package com.giandiport80.topediaapp.core.di

import com.giandiport80.topediaapp.ui.auth.AuthViewModel
import com.giandiport80.topediaapp.ui.navigation.NavViewModel
import com.giandiport80.topediaapp.ui.toko.TokoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { TokoViewModel(get()) }
    viewModel { NavViewModel(get()) }
}