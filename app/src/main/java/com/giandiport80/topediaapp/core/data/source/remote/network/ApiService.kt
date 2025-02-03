package com.giandiport80.topediaapp.core.data.source.remote.network

import com.giandiport80.topediaapp.core.data.source.model.User
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.RegisterRequest
import com.giandiport80.topediaapp.core.data.source.remote.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body login: LoginRequest
        // @Field("email") email: String,
        // @Field("email") password: String,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body data: RegisterRequest
    ): Response<LoginResponse>
}