package com.giandiport80.topediaapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giandiport80.topediaapp.core.data.repository.AppRepository
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.RegisterRequest

class AuthViewModel(private val repo: AppRepository) : ViewModel() {
    fun login(data: LoginRequest) = repo.login(data).asLiveData()

    fun register(data: RegisterRequest) = repo.register(data).asLiveData()
}