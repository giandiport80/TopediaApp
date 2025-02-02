package com.giandiport80.topediaapp.core.di

import com.giandiport80.topediaapp.ui.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
}