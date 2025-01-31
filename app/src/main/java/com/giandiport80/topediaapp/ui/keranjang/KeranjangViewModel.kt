package com.giandiport80.topediaapp.ui.keranjang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KeranjangViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is keranjang"
    }
    val text: LiveData<String> = _text
}