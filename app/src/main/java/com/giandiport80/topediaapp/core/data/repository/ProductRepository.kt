package com.giandiport80.topediaapp.core.data.repository

import android.util.Log
import com.giandiport80.topediaapp.core.data.source.local.LocalDataSource
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.core.data.source.remote.RemoteDataSource
import com.giandiport80.topediaapp.core.data.source.remote.network.Resource
import com.giandiport80.topediaapp.core.data.source.remote.response.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow

class ProductRepository(
    private val local: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getProduct() = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.getProduct().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data

                    emit(Resource.success(data))
                } else {
                    val errorResponse = it.errorBody()?.string()
                    val errorMessage = if (errorResponse != null) {
                        try {
                            val error = Gson().fromJson(errorResponse, ErrorResponse::class.java)
                            error.message ?: "Terjadi kesalahan"
                        } catch (e: Exception) {
                            "Terjadi kesalahan saat mengolah error"
                        }
                    } else {
                        "Terjadi kesalahan"
                    }

                    emit(Resource.error(errorMessage, null))
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
        }
    }

    fun createProduct(data: Product) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.createProduct(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data

                    emit(Resource.success(body))
                } else {
                    val errorResponse = it.errorBody()?.string()
                    val errorMessage = if (errorResponse != null) {
                        try {
                            val error = Gson().fromJson(errorResponse, ErrorResponse::class.java)
                            error.message ?: "Terjadi kesalahan"
                        } catch (e: Exception) {
                            "Terjadi kesalahan saat mengolah error"
                        }
                    } else {
                        "Terjadi kesalahan"
                    }

                    emit(Resource.error(errorMessage, null))
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
        }
    }

    fun updateProduct(data: Product) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.updateProduct(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data

                    emit(Resource.success(body))
                } else {
                    val errorResponse = it.errorBody()?.string()
                    val errorMessage = if (errorResponse != null) {
                        try {
                            val error = Gson().fromJson(errorResponse, ErrorResponse::class.java)
                            error.message ?: "Terjadi kesalahan"
                        } catch (e: Exception) {
                            "Terjadi kesalahan saat mengolah error"
                        }
                    } else {
                        "Terjadi kesalahan"
                    }

                    emit(Resource.error(errorMessage, null))
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
        }
    }

    fun deleteProduct(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.deleteProduct(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data

                    emit(Resource.success(data))
                } else {
                    val errorResponse = it.errorBody()?.string()
                    val errorMessage = if (errorResponse != null) {
                        try {
                            val error = Gson().fromJson(errorResponse, ErrorResponse::class.java)
                            error.message ?: "Terjadi kesalahan"
                        } catch (e: Exception) {
                            "Terjadi kesalahan saat mengolah error"
                        }
                    } else {
                        "Terjadi kesalahan"
                    }

                    emit(Resource.error(errorMessage, null))
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
        }
    }
}