package com.giandiport80.topediaapp.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giandiport80.topediaapp.core.data.repository.AppRepository
import com.giandiport80.topediaapp.core.data.source.remote.request.CreateTokoRequest

class NavViewModel(private val repo: AppRepository) : ViewModel() {
    fun getUser(id: Int? = null) = id?.let { repo.getUser(it).asLiveData() }
}