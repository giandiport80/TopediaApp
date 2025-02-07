package com.giandiport80.topediaapp.core.data.source.remote.network

import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.RegisterRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.UpdateProfileRequest
import com.giandiport80.topediaapp.core.data.source.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @PUT("update-user/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body data: UpdateProfileRequest
    ): Response<LoginResponse>
}