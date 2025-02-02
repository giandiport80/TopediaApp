package com.giandiport80.topediaapp.core.data.source.remote

import com.giandiport80.topediaapp.core.data.source.model.User
import com.giandiport80.topediaapp.core.data.source.remote.network.ApiService
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: LoginRequest) = api.login(data)
}