package com.giandiport80.topediaapp.core.data.repository

import android.util.Log
import com.giandiport80.topediaapp.core.data.source.local.LocalDataSource
import com.giandiport80.topediaapp.core.data.source.model.AlamatToko
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

class AppRepository(
    private val local: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun login(data: LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.login(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    Log.d("SUCCESS_LOGIN", "login sukses:" + it.body().toString())

                    val body = it.body()
                    val user = body?.data

                    Prefs.setUser(user)
                    if (user != null) {
                        Prefs.token = user.token.toString()
                    }
                    emit(Resource.success(user))
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
                    Log.d("ERROR_LOGIN", "login error: $errorMessage")
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
            Log.d("ERROR_LOGIN", "login error: ${error.message}")
        }
    }

    fun register(data: RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.register(data).let {
                if (it.isSuccessful) {
//                    Prefs.isLogin = true
                    Log.d("SUCCESS_REGISTER", "register sukses:" + it.body().toString())

                    val body = it.body()
                    val user = body?.data

//                    Prefs.setUser(user)
                    emit(Resource.success(user))
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
                    Log.d("ERROR_REGISTER", "register error: $errorMessage")
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
            Log.d("ERROR_REGISTER", "register error: ${error.message}")
        }
    }

    fun updateUser(data: UpdateProfileRequest) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.updateUser(data).let {
                if (it.isSuccessful) {
                    Log.d(
                        "SUCCESS_UPDATE_USER",
                        "Update profile success sukses:" + it.body().toString()
                    )

                    val body = it.body()
                    val user = body?.data

                    Prefs.setUser(user)
                    emit(Resource.success(user))
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
                    Log.d("ERROR_UPDATE_USER", "update user error: $errorMessage")
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
            Log.d("ERROR_UPDATE_USER", "update user error: ${error.message}")
        }
    }

    fun uploadImageUser(id: Int, fileImage: MultipartBody.Part? = null) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.uploadImageUser(id, fileImage)?.let {
                if (it.isSuccessful) {
                    Log.d(
                        "SUCCESS_UPLOAD_USER",
                        "Update profile success sukses:" + it.body().toString()
                    )

                    val body = it.body()
                    val user = body?.data

                    Prefs.setUser(user)
                    emit(Resource.success(user))
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
                    Log.d("ERROR_UPLOAD_USER", "update user error: $errorMessage")
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
            Log.d("ERROR_UPLOAD_USER", "update user error: ${error.message}")
        }
    }

    // MODUL TOKO
    fun createToko(data: CreateTokoRequest) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.createToko(data).let {
                if (it.isSuccessful) {
                    Log.d("SUCCESS", "sukses:" + it.body().toString())

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
                    Log.d("ERROR", "create toko error: $errorMessage")
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
            Log.d("ERROR", "create toko error: ${error.message}")
        }
    }

    fun getUser(id: Int) = flow {
        emit(Resource.loading(null))
        try {
            remoteDataSource.getUser(id)?.let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data

                    Prefs.setUser(user)
                    emit(Resource.success(user))
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
                    Log.d("ERROR_GET_USER", "get user error: $errorMessage")
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
            Log.d("ERROR_GET_USER", "get user error: ${error.message}")
        }
    }
}