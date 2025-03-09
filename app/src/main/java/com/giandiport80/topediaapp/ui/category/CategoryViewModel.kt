package com.giandiport80.topediaapp.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giandiport80.topediaapp.core.data.repository.CategoryRepository
import com.giandiport80.topediaapp.core.data.source.model.Category

class CategoryViewModel(private val repo: CategoryRepository) : ViewModel() {
    fun getCategory() = repo.getCategory().asLiveData()

    fun createCategory(data: Category) = repo.createCategory(data).asLiveData()

    fun updateCategory(data: Category) = repo.updateCategory(data).asLiveData()

    fun deleteCategory(id: Int?) = repo.deleteCategory(id).asLiveData()

}