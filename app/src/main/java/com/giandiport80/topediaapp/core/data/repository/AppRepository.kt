package com.giandiport80.topediaapp.core.data.repository

import android.util.Log
import com.giandiport80.topediaapp.core.data.source.local.LocalDataSource
import com.giandiport80.topediaapp.core.data.source.remote.RemoteDataSource
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository(
    private val local: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun login(data: LoginRequest) = flow {
        try {
            remoteDataSource.login(data).let {
                if (it.isSuccessful) {
                    Log.d("SUCCESS_LOGIN", "login sukses:")
                    emit(it.body())
                } else {
                    Log.d("ERROR_LOGIN", "login error:")
                }
            }
        } catch (error: Exception) {
            Log.d("ERROR_LOGIN", "login error: ${error.message}")
        }
    }
}