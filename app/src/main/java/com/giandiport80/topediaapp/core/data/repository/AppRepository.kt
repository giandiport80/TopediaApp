package com.giandiport80.topediaapp.core.data.repository

import android.util.Log
import com.giandiport80.topediaapp.core.data.source.local.LocalDataSource
import com.giandiport80.topediaapp.core.data.source.remote.RemoteDataSource
import com.giandiport80.topediaapp.core.data.source.remote.network.Resource
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
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
                    Log.d("SUCCESS_LOGIN", "login sukses:" + it.body().toString())
                    emit(Resource.success(it.body()?.data))
                } else {
                    emit(Resource.error(it.body()?.message ?: "Terjadi kesalahan", null))
                    Log.d("ERROR_LOGIN", "login error:")
                }
            }
        } catch (error: Exception) {
            emit(Resource.error(error.message ?: "Terjadi kesalahan", null))
            Log.d("ERROR_LOGIN", "login error: ${error.message}")
        }
    }
}