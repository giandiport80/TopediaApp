package com.giandiport80.topediaapp.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giandiport80.topediaapp.core.data.repository.ProductRepository
import com.giandiport80.topediaapp.core.data.source.model.Product
import okhttp3.MultipartBody

class ProductViewModel(private val repo: ProductRepository) : ViewModel() {
    fun getProduct() = repo.getProduct().asLiveData()

    fun createProduct(data: Product) = repo.createProduct(data).asLiveData()

    fun updateProduct(data: Product) = repo.updateProduct(data).asLiveData()

    fun deleteProduct(id: Int?) = repo.deleteProduct(id).asLiveData()

    fun uploadProduct(fileImage: MultipartBody.Part) =
        repo.uploadProduct(fileImage).asLiveData()

}