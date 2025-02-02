package com.giandiport80.topediaapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giandiport80.topediaapp.core.data.repository.AppRepository
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest

class LoginViewModel(private val repo: AppRepository) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Halo selamat datang"
    }
    val text: LiveData<String> = _text

    fun ubahData(value: String) {
        _text.postValue(value)
    }

    fun login(data: LoginRequest) = repo.login(data).asLiveData()
}