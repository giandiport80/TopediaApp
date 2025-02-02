package com.giandiport80.topediaapp.util

import android.app.Application
import com.giandiport80.topediaapp.core.di.appModule
import com.giandiport80.topediaapp.core.di.repositoryModule
import com.giandiport80.topediaapp.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    appModule, viewModelModule, repositoryModule
                )
            )
        }
    }
}