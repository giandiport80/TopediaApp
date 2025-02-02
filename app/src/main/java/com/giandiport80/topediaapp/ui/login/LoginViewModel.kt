package com.giandiport80.topediaapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giandiport80.topediaapp.core.data.repository.AppRepository

class LoginViewModel(val repo: AppRepository) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Halo selamat datang"
    }
    val text: LiveData<String> = _text

    fun ubahData(value: String) {
        _text.postValue(value)
    }
}