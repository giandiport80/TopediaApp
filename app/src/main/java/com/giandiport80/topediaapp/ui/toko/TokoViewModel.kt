package com.giandiport80.topediaapp.ui.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giandiport80.topediaapp.core.data.repository.AppRepository
import com.giandiport80.topediaapp.core.data.source.remote.request.CreateTokoRequest

class TokoViewModel(private val repo: AppRepository) : ViewModel() {
    fun createToko(data: CreateTokoRequest) = repo.createToko(data).asLiveData()

}