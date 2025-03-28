package com.giandiport80.topediaapp.core.di

import com.giandiport80.topediaapp.core.data.repository.BaseViewModel
import com.giandiport80.topediaapp.ui.alamatToko.AlamatTokoViewModel
import com.giandiport80.topediaapp.ui.auth.AuthViewModel
import com.giandiport80.topediaapp.ui.category.CategoryViewModel
import com.giandiport80.topediaapp.ui.home.HomeViewModel
import com.giandiport80.topediaapp.ui.navigation.NavViewModel
import com.giandiport80.topediaapp.ui.product.ProductViewModel
import com.giandiport80.topediaapp.ui.slider.SliderViewModel
import com.giandiport80.topediaapp.ui.toko.TokoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { TokoViewModel(get()) }
    viewModel { NavViewModel(get()) }
    viewModel { AlamatTokoViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { BaseViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SliderViewModel(get()) }
}