package com.giandiport80.topediaapp.core.data.source.remote.network

import com.giandiport80.topediaapp.core.data.source.model.User
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
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
    ): Response<RequestBody>

    @POST("register")
    suspend fun register(
//        @Body user: Any
    ): Response<RequestBody>
}