package com.giandiport80.topediaapp.core.data.source.remote.network

import com.giandiport80.topediaapp.core.data.source.remote.request.CreateTokoRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.RegisterRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.UpdateProfileRequest
import com.giandiport80.topediaapp.core.data.source.remote.response.BaseResponse
import com.giandiport80.topediaapp.core.data.source.remote.response.LoginResponse
import com.giandiport80.topediaapp.core.data.source.remote.response.TokoResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
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

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadImageUser(
        @Path("id") id: Int,
        @Part data: MultipartBody.Part
    ): Response<LoginResponse>

    // TOKO
    @POST("toko")
    suspend fun createToko(
        @Body data: CreateTokoRequest
    ): Response<BaseResponse<TokoResponse>>

    @GET("toko-user/{id}")
    suspend fun getUser(
        @Path("id") id: Int,
    ): Response<LoginResponse>
}