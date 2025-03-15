package com.giandiport80.topediaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giandiport80.topediaapp.core.data.repository.AppRepository
import com.giandiport80.topediaapp.core.data.source.local.DummyData
import com.giandiport80.topediaapp.core.data.source.model.Category
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.core.data.source.model.Slider

class HomeViewModel(private val repo: AppRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Halo selamat datang"
    }
    val text: LiveData<String> = _text

    fun ubahData(value: String) {
        _text.postValue(value)
    }

    val listCategory: LiveData<List<Category>> = MutableLiveData<List<Category>>()

    val listSlider: LiveData<List<Slider>> = MutableLiveData<List<Slider>>().apply {
        value = DummyData.listSlider
    }

    val listProduct: LiveData<List<Product>> = MutableLiveData<List<Product>>()

    fun getHome() = repo.getHome().asLiveData()
}