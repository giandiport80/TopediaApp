package com.giandiport80.topediaapp.core.data.source.remote

import com.giandiport80.topediaapp.core.data.source.model.User
import com.giandiport80.topediaapp.core.data.source.remote.network.ApiService
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.RegisterRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.UpdateProfileRequest

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: LoginRequest) = api.login(data)

    suspend fun register(data: RegisterRequest) = api.register(data)

    suspend fun updateUser(data: UpdateProfileRequest) = api.updateUser(data.id, data)
}