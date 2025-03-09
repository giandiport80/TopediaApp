package com.giandiport80.topediaapp.core.data.repository

import android.util.Log
import com.giandiport80.topediaapp.core.data.source.local.LocalDataSource
import com.giandiport80.topediaapp.core.data.source.model.Category
import com.giandiport80.topediaapp.core.data.source.remote.RemoteDataSource
import com.giandiport80.topediaapp.core.data.source.remote.network.Resource
import com.giandiport80.topediaapp.core.data.source.remote.request.CreateTokoRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.RegisterRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.UpdateProfileRequest
import com.giandiport80.topediaapp.core.data.source.remote.response.ErrorResponse
import com.giandiport80.topediaapp.core.data.source.remote.response.LoginResponse
import com.giandiport80.topediaapp.util.Prefs
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class CategoryRepository(
    private val local: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getCategory() = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.getCategory().let {
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

    fun createCategory(data: Category) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.createCategory(data).let {
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

    fun updateCategory(data: Category) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.updateCategory(data).let {
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

    fun deleteCategory(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.deleteCategory(id).let {
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