package com.giandiport80.topediaapp.core.data.repository

import android.util.Log
import com.giandiport80.topediaapp.core.data.source.local.LocalDataSource
import com.giandiport80.topediaapp.core.data.source.remote.RemoteDataSource
import com.giandiport80.topediaapp.core.data.source.remote.network.Resource
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.core.data.source.remote.response.ErrorResponse
import com.giandiport80.topediaapp.core.data.source.remote.response.LoginResponse
import com.giandiport80.topediaapp.util.Prefs
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
}