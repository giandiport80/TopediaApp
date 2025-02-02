package com.giandiport80.topediaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Halo selamat datang"
    }
    val text: LiveData<String> = _text

    fun ubahData(value: String) {
        _text.postValue(value)
    }
}