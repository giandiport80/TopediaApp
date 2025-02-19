package com.giandiport80.topediaapp.ui.alamatToko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giandiport80.topediaapp.core.data.repository.AppRepository
import com.giandiport80.topediaapp.core.data.source.remote.request.CreateTokoRequest

class AlamatTokoViewModel(private val repo: AppRepository) : ViewModel() {
    fun getAlamatToko() = repo.getAlamatToko().asLiveData()
}